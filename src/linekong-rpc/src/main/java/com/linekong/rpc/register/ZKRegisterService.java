package com.linekong.rpc.register;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.utils.ZKEnvirmentUtils;
/**
 * zookeeper注册服务
 */
public class ZKRegisterService {

	private static ZooKeeper zooKeeper;

	private static ReentrantLock lock = new ReentrantLock(true);

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
	public static void destory(){
		if (zooKeeper != null) {
			try {
				zooKeeper.close();
			} catch (InterruptedException e) {
				LoggerUtils.error(ZKRegisterService.class, "zookeeper exception :" + e.getMessage());
			}
		}
	}
	/**
	 * zookeeper注册服务
	 * @param String zkAddress
	 *               zookeeper地址
	 * @param int zkSessionTimeOut
	 *               zookeeper回话超时时间
	 * @param int port
	 *            注册对外提供服务的端口
	 * @param Set<String> serviceList
	 *            注册对外提供服务的列表
	 */
    public static void registerServices(int port, Set<String> serviceList) throws KeeperException, InterruptedException {
    	// valid
    	if (port < 1 || (serviceList==null || serviceList.size()==0)) {
    		LoggerUtils.error(ZKRegisterService.class, "register zookeeper info error port < 1 or serviceList.size <== 0");
    		return;
    	}
    	// init address: ip : port
    	String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		if (ip == null) {
			LoggerUtils.error(ZKRegisterService.class, "can't get local ip when register server");
			return;
		}
		String serverAddress = ip + ":" + port;
		// base path
		Stat stat = getInstance().exists(ZKEnvirmentUtils.ZK_Path, true);
		if (stat == null) {
			getInstance().create(ZKEnvirmentUtils.ZK_Path, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		// register
		for (String interfaceName : serviceList) {
			// init servicePath prefix : servicePath : linekong-rpc/interfaceName/serverAddress(ip01:port9999)
			String ifacePath = ZKEnvirmentUtils.ZK_Path.concat("/").concat(interfaceName);
			String addressPath = ZKEnvirmentUtils.ZK_Path.concat("/").concat(interfaceName).concat("/").concat(serverAddress);
			// ifacePath(parent) path must be PERSISTENT
			Stat ifacePathStat = getInstance().exists(ifacePath, true);
			if (ifacePathStat == null) {
				getInstance().create(ifacePath, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			// register service path must be EPHEMERAL
			Stat addreddStat = getInstance().exists(addressPath, true);
			if (addreddStat != null) {
				getInstance().delete(addressPath, -1);
			}
			getInstance().create(addressPath, serverAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			LoggerUtils.info(ZKRegisterService.class,"linekong-rpc register service on zookeeper success, interfaceName:"+interfaceName+", serverAddress:"+serverAddress+", addressPath:"+addressPath+"");
		}

    }

}
