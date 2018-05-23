package com.linekong.rpc.register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.utils.ZKEnvirmentUtils;

/**
 * zookeeper发现服务
 */
public class ZKDiscoveryService {

	private static ZooKeeper zooKeeper;

	private static ReentrantLock lock = new ReentrantLock(true);

	private static volatile ConcurrentMap<String, Set<String>> serviceAddress = new ConcurrentHashMap<String, Set<String>>();

	/**
	 * 初始化zookeeper
	 */
	public static ZooKeeper getInstance() {
		if (zooKeeper == null) {
			try {
				if (lock.tryLock(2, TimeUnit.SECONDS)) {
					try {
						zooKeeper = new ZooKeeper(ZKEnvirmentUtils.ZK_ADDDRESS, ZKEnvirmentUtils.ZK_SESSION_TIMEOUT, new Watcher() {
							@Override
							public void process(WatchedEvent event) {
								// session expire, close old and create new
								if (event.getState() == Event.KeeperState.Expired) {
									try {
										zooKeeper.close();
									} catch (InterruptedException e) {
										LoggerUtils.error(ZKRegisterService.class,
												"zookeeper exception :" + e.getMessage());
									}
									zooKeeper = null;
								}
								// add One-time trigger, ZooKeeper的Watcher是一次性的，用过了需要再注册
								try {
									String znodePath = event.getPath();
									if (znodePath != null) {
										zooKeeper.exists(znodePath, true);
									}
								} catch (KeeperException e) {
									LoggerUtils.error(ZKRegisterService.class,
											"KeeperException exception :" + e.getMessage());
								} catch (InterruptedException e) {
									LoggerUtils.error(ZKRegisterService.class,
											"InterruptedException exception :" + e.getMessage());
								}
							}
						});
						LoggerUtils.info(ZKRegisterService.class, "linekong-rpc zookeeper connnect success");
					} finally {
						lock.unlock();
					}
				}
			} catch (InterruptedException e) {
				LoggerUtils.error(ZKRegisterService.class, "ReentrantLock exception :" + e.getMessage());
			} catch (IOException e) {
				LoggerUtils.error(ZKRegisterService.class, "zookeeper exception :" + e.getMessage());
			}
		}
		if (zooKeeper == null) {
			throw new NullPointerException("linekong-rpc, zookeeper connect fail.");
		}
		return zooKeeper;
	}

	/**
	 * 销毁zookeeper连接
	 */
	public static void destory() {
		if (zooKeeper != null) {
			try {
				zooKeeper.close();
			} catch (InterruptedException e) {
				LoggerUtils.error(ZKRegisterService.class, "zookeeper exception :" + e.getMessage());
			}
		}
	}

	/**
	 * 刷新服务
	 */
	private static void freshServiceAddress() {
		ConcurrentMap<String, Set<String>> tempMap = new ConcurrentHashMap<String, Set<String>>();
		try {
			// iface list
			List<String> interfaceNameList = getInstance().getChildren(ZKEnvirmentUtils.ZK_Path, true);

			if (interfaceNameList != null && interfaceNameList.size() > 0) {
				for (String interfaceName : interfaceNameList) {

					// address list
					String ifacePath = ZKEnvirmentUtils.ZK_Path.concat("/").concat(interfaceName);
					List<String> addressList = getInstance().getChildren(ifacePath, true);

					if (addressList != null && addressList.size() > 0) {
						Set<String> addressSet = new HashSet<String>();
						for (String address : addressList) {
							// data from address
							String addressPath = ifacePath.concat("/").concat(address);
							byte[] bytes = getInstance().getData(addressPath, false, null);
							addressSet.add(new String(bytes));
						}
						tempMap.put(interfaceName, addressSet);
					}
				}
				serviceAddress = tempMap;
				LoggerUtils.info(ZKDiscoveryService.class, "linekong-rpc fresh serviceAddress success: serviceAddress");
			}

		} catch (KeeperException e) {
			LoggerUtils.error(ZKDiscoveryService.class, "KeeperException:" + e.getMessage());
		} catch (InterruptedException e) {
			LoggerUtils.error(ZKDiscoveryService.class, "InterruptedException:" + e.getMessage());
		}
	}

	/**
	 * 服务发现
	 */
	public static String discover(String interfaceName) {
		freshServiceAddress();
		Set<String> addressSet = serviceAddress.get(interfaceName);
		if (addressSet == null || addressSet.size() == 0) {
			return null;
		}
		String address;
		List<String> addressArr = new ArrayList<String>(addressSet);
		int size = addressSet.toArray().length;
		if (size == 1) {
			address = addressArr.get(0);
		} else {
			address = addressArr.get(new Random().nextInt(size));
		}
		return address;
	}

}
