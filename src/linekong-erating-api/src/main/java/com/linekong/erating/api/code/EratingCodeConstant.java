package com.linekong.erating.api.code;

public class EratingCodeConstant {
	
	//成功操作码
	public static final int S_SUCCESS        = 1;    //表示操作成功
	
	public static final int S_LOGIN_HIGHSEC  = 101;  //正常注册带安全保护时登录成功的状态
	
	public static final int S_LOGIN_LOWSEC   = 102;  //未进行安全保护时登录成功的状态(多指新手卡账号)

	public static final int S_MODIFY_SUCURITY_CODE = 103; //返回邮件验证成功标示
	
	public static final int S_MODIFY_PASSWORD = 104;  //修改密码相关操作成功
	
	//失败错误码
	public static final int E_ERROR = 0;    //表示操作失败或操作错误。未知错误。
	
	public static final int E_PDU_ERROR = -1;  //协议未知错误。
	
	public static final int E_PDU_LENGTH_ERROR = -2;  //协议包长度错误。
			
	public static final int E_PDU_PARSE_ERROR = -3;  //协议包解析时发生解析错误。
	
	public static final int E_PDU_CHECKSUM_ERROR = -4;  //协议包校验错误。
	
	public static final int E_PDU_INVALID_PROTOCOL = -5;  //无效的协议号。
	
	public static final int E_PDU_TIME_OUT = -6;  //协议包接收超时。
	
	public static final int E_PDU_INVALID_FIELD = -7;  //协议字段错误。
	
	public static final int E_PDU_UNKNOWN_VERSION = -8;  //未知版本或非法版本的协议。
	
	public static final int E_PARAMETER_ERROR = -100;  //调用数据库包的方法传递参数有误（指的是包含非法字符或是长度不够）
	
	public static final int E_SYS_ERROR = -200;  //系统内部错误。
			
	public static final int E_SYS_NOT_ENOUGH_MEMORY = -201; //内存失败或内存不足。
	
	public static final int E_SYS_TIMEOUT = -202;  //系统超时。
	
	public static final int E_SYS_THREAD_CREATE_ERROR = -210;  //线程创建错误。
	
	public static final int E_SYS_MOCK_ERROR = -211;  //mock同义词调用错误。
	
	public static final int E_SYS_NET_ERROR = -300;  //网络错误。
	
	public static final int E_SYS_NET_CONNECTION_ERROR = -301;  //网络连接错误。
			
	public static final int E_SYS_NET_TIMEOUT = -302;  //网络超时。
			
	public static final int E_SYS_NET_IP_ERROR = -303;  //IP地址错误。
			
	public static final int E_SYS_NET_IP_BIND_ERROR = -304;  //绑定IP地址时错误。
			
	public static final int E_SYS_NET_SERVER_START_ERROR = -305;  //服务启动错误。
			
	public static final int E_SYS_NET_RECV_DATA_ERROR = -306;  //接收数据失败。
			
	public static final int E_SYS_NET_SEND_DATA_ERROR = -307;  //接收发送失败。
			
	public static final int E_SYS_NET_TOO_MANY_CONNECTION = -308;  //超过最大连接数。
			
	public static final int E_SYS_NET_NOT_ENOUGH_BUFFER = -309;  //缓存不足。
		
	public static final int E_SYS_NET_CLOSED = -310; //网络已断开。
			
	public static final int E_SYS_NET_INVALID = -311;  //网络不可用。
			
	public static final int E_SYS_NET_OUT_OF_MAX_TIMES = -312;  //超出最大尝试次数。
			
	public static final int E_SYS_MSG_QUEUE_FULL = -313;  //消息队列已满。
			
	public static final int E_SYS_MSG_QUEUE_TIMEOUT = -314;  //消息在队列中超时。
			
	public static final int E_SYS_MSG_REQUEST_ILLEGAL = -315;  //非法请求。
			
	public static final int E_SYS_MSG_RESPONSE_ILLEGAL = -316;  //非法应答。

	public static final int E_SYS_LICENSE_ERROR = -400;  //License错误。
			
	public static final int E_SYS_LICENSE_OVERFLOW = -401;  //超过License允许的个数。
			
	public static final int E_SYS_LICENSE_EXPIRED = -402;  //License已过期。
			
	public static final int E_SYS_DATABASE_ERROR = -500;  //数据库错误。
			
	public static final int E_SYS_DATABASE_CONNECT_ERROR = -501;  //数据库连接错误。
			
	public static final int E_SYS_DATABASE_UIDPWD_ERROR = -502;  //数据库用户名或密码错误。
			
	public static final int E_SYS_DATABASE_TIMEOUT = -503;  //数据库超时。
			
	public static final int E_SYS_DATABASE_NO_CONNECTION = -504;  //数据库没连接。
			
	public static final int E_SYS_DATABASE_DATA_INVALID = -505;  //数据非法，主要指有校验位的关键数据，数据与校验位不合法的问题。
			
	public static final int E_SYS_CONFIG_ERROR = -600;  //系统配置错误。
			
	public static final int E_SYS_CONFIG_NO_ITEM = -601;  //无相应配置项。
			
	public static final int E_SYS_FILE_NOT_FOUND = -700;  //文件未找到。
		
	public static final int E_SYS_FILE_RW_ERROR = -701;  //文件读写错误。
			
	public static final int E_SYS_FILE_DISK_FULL = -702;  //磁盘满。
			
	public static final int E_SYS_PATH_NOT_FOUND = -800;  //文件路径未找到。
			
	public static final int E_SYS_PATH_CREATE_ERROR = -801;  //创建路径失败。
			
	public static final int E_SYS_PATH_CHANGE_ERROR = -802;  //改变路径失败。
			
	public static final int E_SYS_PATH_RW_ERROR = -803;  //访问路径时失败。

	public static final int E_GATEWAY_ERROR = -1000;  //网关未知错误。
			
	public static final int E_GATEWAY_NOT_FOUND = -1001;  //网关未找到或不存在。
			
	public static final int E_GATEWAY_UID_PWD_ERROR = -1002;  //网关帐号或密码错。
			
	public static final int E_GATEWAY_IP_ERROR = -1003;  //网关IP地址错误。
			
	public static final int E_GATEWAY_MAC_ERROR = -1004; //网关MAC地址错误。
			
	public static final int E_GATEWAY_STATE_ERROR = -1010;  //网关状态错误。
			
	public static final int E_GATEWAY_STATE_IS_ONLINE = -1011;  //网关已经在线。
			
	public static final int E_GATEWAY_STATE_IS_OFFLINE = -1012;  //网关已经下线。
			
	public static final int E_GATEWAY_STATE_IS_BUSY = -1013;  //网关繁忙。
			
	public static final int E_GATEWAY_DIFF = -1014;  //网关不一致。
			
	public static final int E_ZONE_ERROR = -1100; //区错误。
			
	public static final int E_ZONE_NOT_FOUND = -1101;  //区不存在。
			
	public static final int E_ZONE_STATE_ERROR = -1110;  //区状态错误。
			
	public static final int E_GAME_ID_ERROR = -1120;  //游戏ID错误。
			
	public static final int E_GAME_ID_EXISTED = -1121;  //游戏ID已存在
			
	public static final int E_GAME_ID_NOT_EXIST = -1122;  //游戏ID不存在
			
	public static final int E_ACCOUNT_ERROR = -1200;  //帐号非法或错误。
			
	public static final int E_ACCOUNT_NOT_FOUND = -1201;  //玩家帐号不存在。
			
	public static final int E_ACCOUNT_INVALID = -1202;  //帐号名出现非法字符。
			
	public static final int E_ACCOUNT_LENGTH_ERROR = -1203;  //帐号长度错误。
			
	public static final int E_ROLE_DUPLICATED = -1254;  //角色在同区中重复创建。
			
	public static final int E_ROLE_ERROR = -1250;  //角色错误。
	
	public static final int E_ROLE_EXIST = -1251;  //角色已经存在。
			
	public static final int E_ROLE_NOT_EXIST = -1252;  //角色不存在。
			
	public static final int E_ROLE_DELETED = -1253;  //角色已经被删除。
			
	public static final int E_ROLE_IS_ONLINE = -1255;  //角色在线
			
	public static final int E_ROLE_IS_OFFLINE = -1256;  //角色下线。
			
	public static final int E_ROLE_STATE_NORMAL = -1257;  //角色状态正常
			
	public static final int E_ROLE_LEVEL_LIMITED = -1261;  //角色级别不满足
			
	public static final int E_ROLE_WITH_GROUP = -1262;  //角色删除时不能拥有帮派

	public static final int E_IB_PAY_DETAIL_ERROR = -1280;  //消费流水错误。
			
	public static final int E_IB_PAY_DETAIL_NOT_EXIST = -1281;  //消费流水不存在。
			
	public static final int E_IB_PAY_DETAIL_DUPLICATE = -1282;  //消费流水重复。
			
	public static final int E_FUND_SUMMARY_NOT_EXIST = -1283;  //帐户summary记录不存在。
			
	public static final int E_MANUAL_DRAW_DENYED = -1290;  //不允许手工领取。
			
	public static final int E_GLOBAL_RECHARGE_DENYED = -1291;  //不允许全局（全区全服）充值。
			
	public static final int E_USER_ERROR = -1400;  //玩家的出现未知错误。
			
	public static final int E_USER_ACCOUNT_PASSWORD_ERROR = -1401;  //玩家的帐号或密码错误。
			
	public static final int E_USER_PASSWORD_ERROR = -1402;  //登陆密码和帐号不匹配
			
	public static final int E_USER_SECURITYPW_ERROR = -1403;  //安全码和帐号不匹配
			
	public static final int E_USER_EMAILCODE_ERROR = -1404;  //通过邮箱修改安全码的验证码不匹配
			
	public static final int E_USER_NOEMAIL_ERROR = -1405;  //该账号没有绑定邮箱
			
	public static final int E_USER_ACCOUNT_ERROR = -1406;  //帐号不存在
			
	public static final int E_USER_REG_EXIST_ERROR = -1407;  //帐号名已存在(注册时判断）
			
	public static final int E_USER_USERINFO_ERROR = -1408;  //该账号没有设置个人身份信息
			
	public static final int E_USER_NOTINGAME_ERROR = -1409;  //该帐号还没有登录过游戏
			
	public static final int E_IDCODE_TIMES_ERROR = -1433;  //身份证号使用次数过多
			
	public static final int E_IDCODE_FORMAT_ERROR = -1419;  //身份证格式错误
			
	public static final int E_IDCODE_CONFLICT_ERROR = -1424;  //身份证绑定冲突
			
	public static final int E_USER_TOKEN_ERROR = -1418;  //用户token认证失败
			
	public static final int E_USER_STATE_ERROR = -1410;  //玩家状态错。
			
	public static final int E_USER_STATE_FREEZED = -1411;  //玩家已被冻结。
			
	public static final int E_USER_STATE_EXPIRED = -1412;  //玩家已过期。
			
	public static final int E_USER_STATE_NOT_ACTIVATED = -1413;  //玩家未激活。
			
	public static final int E_USER_STATE_ACTIVATED = -1414;  //玩家已激活
			
	public static final int E_USER_PASSWORD_NOT_EXIST = -1415;  //游戏内密码未设置。
			
	public static final int E_USER_STATE_NORMAL = -1416;  //状态正常
			
	public static final int E_USER_STATE_FREEZED_BY_IDCODE = -1417;  //由于身份证帐号使用次数过多，玩家帐号被冻结
			
	public static final int E_USER_TYPE_ERROR = -1420;  //玩家的类型不正确。
			
	public static final int E_USER_MATRIX_PASSWD_REQUIRED = -1421;  //必须使用密保卡验证。
			
	public static final int E_USER_DYNAMIC_PSW_REQUIRED = -1422;  //必须使用动态密保验证。
			
	public static final int E_USER_PHONE_LOCK_PSW_REQUIRED = -1423;  //需要解开电话锁。
			
	public static final int E_USER_IN_OTHER_GATEWAY = -1430;  //玩家已在其它网关登录。
			
	public static final int E_USER_MAC_ILLEGAL = -1431;  //玩家所在主机的MAC不合法。
			
	public static final int E_USER_HARDWARE_SN_ILLEGAL = -1432;  //玩家所在主机的硬件序列号不合法。
			
	public static final int E_USER_GATEWAY_AUTHEN_DENIED = -1434;  //玩家不允许在此网关登陆认证
			
	public static final int E_USER_LOGIN_INVALID = -1435;  //用户登录无效
			
	public static final int E_USER_LOGIN_EXPIRED = -1436;  //用户登录已过期

    public static final int E_USER_BALANCE_ERROR = -1440;  //余额错误。
			
    public static final int E_USER_BALANCE_NOT_ENOUGH = -1441;  //余额不足。
			
    public static final int E_USER_BALANCE_POINTS_NOT_ENOUGH = -1442;  //计点不足。
			
    public static final int E_USER_BALANCE_DATE_EXPIRED = -1443;  //包时已过期。
			
    public static final int E_USER_BALANCE_COIN_NOT_ENOUGH = -1444;  //金币余额不足。
			
    public static final int E_BALANCE_TOO_MANY_SUBJECTS = -1445;  //余额科目太多。
			
    public static final int E_FUND_NOT_FOUND = -1446;  //账户未找到
			
    public static final int E_FUND_DETAIL_NOT_FOUND = -1447;  //充值明细未找到
			
    public static final int E_FUND_LOG_NOT_FOUND = -1448;  //充值日志未找到
			
    public static final int E_FUND_LOG_DUPLICATE = -1449; // 充值log_id重复
			
    public static final int E_USER_IB_ITEM_ERROR = -1450;  //IB物品错误。
			
    public static final int E_USER_IB_ITEM_NOT_FOUND = -1451;  //IB物品未找到。
			
    public static final int E_USER_IB_ITEM_TYPE_ERROR = -1452;  //IB物品类型错误。
			
    public static final int E_USER_IB_ITEM_USE_TYPE_ERROR = -1453;  //IB物品使用类型错误。
			
    public static final int E_USER_IB_ITEM_PRICE_ERROR = -1454;  //IB物品价格错误。
			
    public static final int E_USER_IB_ITEM_DURATION_ERROR = -1455;  //IB物品有效期错误。
			
    public static final int E_USER_IB_ITEM_EXISTED = -1456;  //IB物品已存在。
			
    public static final int E_USER_IB_ITEM_NOT_ENOUGH = -1457;  //IB物品剩余数不足。
			
    public static final int E_USER_IB_ITEM_USED = -1458;  //IB物品已被使用。
			
    public static final int E_USER_IB_ITEM_EXPIRED = -1459;  //IB物品已过期。
			
    public static final int E_PAY_DUPLICATE = -1460;  //IB物品消费流水号重复。
			
    public static final int E_USER_ITEM_NOT_FOUND = -1461;  //活动物品未找到。
			
    public static final int E_USER_ITEM_NOT_ENOUGH = -1462;  //活动物品剩余数量不足。
			
    public static final int E_ITEM_ORDER_CODE_DUPLICATE = -1463;  //活动奖励订单号重复。
			
    public static final int E_USER_ITEM_DUP = -1464;  //活动奖励重复发放。
			
    public static final int E_USER_ITEM_END_TIME_INVALID = -1465;  //活动奖励过期时间无效。
			
    public static final int E_USER_ITEM_NUMBER_INVALID = -1466;  //活动奖励数量无效。
			
    public static final int E_USER_ITEM_NUMBER_TOO_LARGE = -1467;  //活动奖励数量超过最大值。
			
    public static final int E_USER_ITEM_UPDATE_ERROR = -1468;  //更新活动奖励数量失败。
			
    public static final int E_PACKAGE_ITEM_NOT_FOUND = -1469;  //包中未找到指定道具。

    public static final int E_USER_CHARGE_ERROR = -1470;  //玩家充值时发生错误。
			
    public static final int E_USER_CHARGE_STATE_ERROR = -1471;  //玩家充值状态错误。
			
    public static final int E_CHARGE_DUPLICATE = -1472;  //充值流水号重复。
			
    public static final int E_DETAIL_ID_NOT_FOUND = -1473;  //流水号未找到。
			
    public static final int E_USER_UNCHARGE_DUPLICATE = -1474;  //反充值流水号重复。
			
    public static final int E_CARD_NOT_EXIST = -1475;  //卡号不存在。
			
    public static final int E_CARD_PASSWORD_ERROR = -1476;  //卡密码错误。
			
    public static final int E_CARD_ALREADYUSE = -1477;  //卡已经被使用。
			
    public static final int E_USER_UNCHARGE_NOT_FOUND = -1478;  //用户反充值时，相应的位置未找到。
			
    public static final int E_USER_UNCHARGE_HAS_USED = -1479;  //用户反充值时，相应的充值记录已被使用。
			
    public static final int E_USER_SERIALNO_ERROR = -1480;  //序列号使用错误。
			
    public static final int E_USER_SERIALNO_NOT_FOUND = -1481;  //序列号不存在。
			
    public static final int E_USER_SERIALNO_USED = -1482;  //序列号已使用。
			
    public static final int E_USER_SERIALNO_EXPIRED = -1483;  //序列号已过期。
			
    public static final int E_USER_SERIALNO_EXISTED = -1484;  //序列号已存在。
			
    public static final int E_USER_SERIALNO_NOT_STARTED = -1485;  //序列号未开启

    public static final int E_USER_ERROR_CHCAGE_DETAIL_ID = -1490;  //充值流水号不存在。
			
    public static final int E_CARD_HASEXPIRE = -1491;  //卡已过期
			
    public static final int E_CARD_NOT_ASSIGNED = -1492;  //卡号未分配
			
    public static final int E_CARD_NOT_ACTIVE = -1493;  //卡号未激活
			
    public static final int E_CARD_FREEZED = -1494;  //卡号被临时冻结
			
    public static final int E_CARD_FOREVER_FREEZED = -1495;  //卡号被永久冻结
			
    public static final int E_BATCH_NOT_SEND_ITEM = -1495;  //此批次号不送礼
			
    public static final int E_CARD_FULL = -1499;  //此类型卡已满，不能再生成

	public static final int E_USER_NO_POINT = -1502;  //用充值积分不够。
			
	public static final int E_USER_NOT_ENOUGH_POINT = -1501;  //用户积分不够。
			
	public static final int E_ACTIVITY_DATE_END = -1505;  //活动时间结束。
			
	public static final int E_ACTIVITY_ERROR_NUMBER = -1508;  //活动配置异常。
			
	public static final int E_ACTIVITY_MULACTIVITY_ID = -1509;  //一个游戏一段时间不能同时有多个activityid。
			
	public static final int E_ACTIVITY_ID_EXIST = -1510;  //输入的活动号重复
			
	public static final int E_ACTIVITY_TIME = -1511;  //输入的时间错误。
			
	public static final int E_ACTIVITY_MONEY = -1512;  //输入的钱数错误。
			
	public static final int E_ACTIVITY_PARAMTER = -1513;  //输入的参数不合法。
			
	public static final int E_ACTIVITY_NOT_FOUND = -1514;  //活动未找到。
			
	public static final int E_ACTIVITY_STARTED = -1515;  //活动已开启。
			
	public static final int E_CHARGE_AMOUNT_ERROR = -1516;  //充值金额非法。
			
	public static final int E_ACTIVITY_CLOSED = -1517;  //活动已关闭。
			
	public static final int E_PACKAGE_NOT_EXIST = -1518;  //礼包不存在。
			
	public static final int E_CONDITION_CONFLICT = -1519;  //活动条件冲突
			
	public static final int E_ACTIVITY_DETAIL_EXISTS = -1520;  //活动详情已存在
			
	public static final int E_PASSCARD_NOT_EXIST = -1521;  //密保卡不存在
			
	public static final int E_PASSCARD_NOT_CORRECT = -1522;  //密保卡密码错
			
	public static final int E_PASSCARD_CANNOT_USE = -1523;  //密保卡不可用
			
	public static final int E_PASSCARD_CANNOT_VALIDATE = -1524;  //密保卡验证过程中出错（比如：位置参数不符合格式）
			
	public static final int E_PASSCARD_ALREADY_BIND = -1525;  //密保卡已绑定
			
	public static final int E_PASSCARD_ALREADY_BIND_USER = -1526;  //密保卡已绑定到别的用户上
			
	public static final int E_USER_NO_PHONE_LOCK = -1530;  //用户没有开启手机锁
			
	public static final int E_OLD_PASSCARD_NOT_EXIST = -1531;  //旧密保卡不存在
			
	public static final int E_OLD_PASSCARD_NOT_CORRECT = -1532;  //旧密保卡密码错
			
	public static final int E_OLD_PASSCARD_NOT_BIND = -1533;  //旧密保卡没有绑定此款游戏
			
	public static final int E_USER_NO_PASSCARD_BIND = -1534;  //用户没有绑定任何密保卡
			
	public static final int E_SN_NOT_EXIST = -1536;  //动态密保卡序列号不存在
			
	public static final int E_SN_NOT_MACTH = -1535;  //用户输入的密保卡和用户绑定的不一致
			
	public static final int E_DYNAMIC_PWD_ERROR = -1537;  //动态密码错误
			
	public static final int E_DCODE_ALREADY_BIND = -1538;  //已绑定动态密保卡	
			
	public static final int E_USER_NO_DYNAMIC_PSW_BIND = -1539;  //用户没有绑定动态密保。
			
	public static final int E_DCODE_BIND_OTHER_USER = -1540;  //序列号已绑定其它帐号
			
	public static final int E_DYNAMIC_PWD_INVALID = -1541;  //动态密码已失效，请待更新后重新输入或需进行同步操作
			
	public static final int E_DYNAMIC_PWD_LOCKED = -1542;  //动态密保已锁定
			
	public static final int E_DYNAMIC_PWD_UNKNOWN_ERROR = -1543;  //动态密保未知错误
			
	public static final int E_DYNAMIC_PWD_REG_LOSSED = -1544;  //动态密保已挂失
			
	public static final int E_DYNAMIC_EXPORED = -1545;  //动态令牌已过期
			
	public static final int E_DYNAMIC__SECPWD_ERROR = -1546;  //第二次输入的动态密码错误
			
	public static final int E_DSN_SERVICE_CODE = -1547;  //动态令牌服务码错误
			
	public static final int E_DYNAMIC_DISABLED = -1548;  //动态令牌禁用
			
	public static final int E_DYNAMIC_NEED_SYNCPWD_ERROR = -1549;  //动态密码与服务器不一致，需要进行校准
			
	public static final int RET_E_DYNAMIC_LOST_STATIC = -1550;  //令牌挂失（静态密码方式）
			
	public static final int E_ACTIVITY_GATEWAY_EXISTS = -1551;  //活动区组已存在
			
	public static final int E_PACKAGE_ITEM_EXISTS = -1552;  //礼包道具已存在
			
	public static final int E_CONDITION_NOT_SATISFIED = -1553;  //活动条件不满足
			
	public static final int E_ACTIVITY_NO_GATEWAY = -1554;  //未配置活动区组
			
	public static final int E_ACTIVITY_NO_DETAIL = -1555;  //未配置活动详情
			
	public static final int E_ACTIVITY_NO_PACKAGE = -1556;  //未配置活动礼品包
			
	public static final int E_ACTIVITY_COUNT_ERROR = -1557;  //活动数量错误
			
	public static final int E_SMS_BUY_EXCEED_LIMIT = -1558;  //手机短信购买已达到次数限制
			
	public static final int E_ACTIVITY_FLAG_NOT_ENOUGH = -1559;  //活动标志不足
			
	public static final int E_ACTIVITY_MUST_MUTEX = -1560;  //活动必需设置为互斥
			
	public static final int E_ACTIVITY_CANNOT_MUTEX = -1561;  //活动不能设置为互斥
			
	public static final int E_ACTIVITY_GATEWAY_DIFF = -1562;  //活动网关配置冲突
			
	public static final int E_ACTIVITY_DESC_EXIST = -1563;  //活动描述已存在
			
	public static final int E_ACTIVITY_DESC_NOT_EXIST = -1564;  //活动描述不存在
			
	public static final int E_PARTICIPATE_TIMES_EXCEEDED = -1565;  //参加活动次数超出限制
			
	public static final int E_POOL_BALANCE_NOT_ENOUGH = -1566;  //奖池活动余额不足

    public static final int E_PROMOTER_BIND_FAIL = -1601;  //链接推广时绑定推广员失败（但是能让玩家注册）
			
    public static final int E_USER_NO_DIAMOND = -1611;  //玩家帐号上没有蓝钻
			
    public static final int E_USER_NOT_ENHOUGH_DIAMOND = -1612;  //玩家帐号上蓝钻不足够
			
    public static final int E_USER_ALREADY_BIND = -1651;  //玩家帐号已绑定
			
    public static final int E_CANNOT_MAKE_BIND = -1652;  //不能建立绑定关系

    public static final int E_USER_NOT_CHARGE = -1701;  //用户未充值
			
    public static final int E_USER_REBATED = -1702;  //用户已参与返现
			
    	public static final int E_REBATE_ID_USED = -1703;  //返现金ID已被使用

    	public static final int E_PHONE_NO_USED = -1704;  //电话号码已被使用

    	public static final int E_MAC_USED = -1705;  //MAC已被使用
			
    	public static final int E_TOKEN_NOT_FOUND = -1709;  //Token未找到
			
    	public static final int E_TOKEN_EXPIRED = -1710;  //Token已过期
			
    	public static final int E_TOKEN_USED = -1711;  //Token已被使用
			
    	public static final int E_PHONE_NOT_MATCH = -1713;  //玩家手机号码不匹配
			
    	public static final int E_ONLINE_TIME_NOT_ENOUGH = -1714;  //玩家在线时长不够

    	public static final int E_USER_SENDED = -1706;  //已给此帐号发送过
			
    	public static final int E_PHONE_NO_SENDED = -1707;  //已给此电话号码发送过
			
    	public static final int E_MAC_SENDED = -1708;  //已给此MAC发送过

    	public static final int E_DEVICE_ID_EXISTED = -1715;  //设备ID已存在
			
    	public static final int E_DEVICE_ID_NOT_EXISTED = -1716;  //设备ID不存在

    	public static final int E_JOINT_MSG_ERROR = -1811;  //合作运营通信消息错误
			
    	public static final int E_JOINT_ACCOUNT_ERROR = -1812;  //合作运营帐户错误。
			
    	public static final int E_JOINT_SIGN_ERROR = -1813;  //签名错误。
			
    	public static final int E_JOINT_EXCEED_INVOKE_COUNT = -1814;  //超过方法最大调用次数限制
			
    	public static final int E_JOINT_METHOD_DENY = -1815;  //接口拒绝访问
			
    	public static final int E_TMP_USER_NOT_SUPPORT = -1816;  //临时账号不支持此功能

	public static final int E_GROUP_NOT_EXIST = -1900;  //团体不存在。
			
	public static final int E_GROUP_EXIST = -1901;  //团体已存在。
			
	public static final int E_GROUP_DUPLICATE = -1902;  //团体被拥有者重复创建。
			
	public static final int E_GROUP_DELETED = -1903;  //团体已被删除。
			
	public static final int E_NOT_GROUP_OWNER = -1904;  //非团体拥有者。

	public static final int RET_E_DYNAMIC_NOT_LOST = -2001;  //动态令牌没有挂失
			
	public static final int RET_E_DSN_DISABLED_AND_BIND = -2002;  //动态令牌已停用,如想再绑定其它令牌请进行令牌更换操作
			
	public static final int RET_E_DYNAMIC_BOND_IDERR = -2003;  //输入身份证信息与绑定时身份证信息不符
			
	public static final int RET_E_DYNAMIC_SYN_ERROR = -2004;  //令牌校准失败
			
	public static final int RET_E_DYNAMIC_NOT_LOCK = -2005;  //令牌未锁定

	public static final int E_OPTION_CODE_NOT_EXIST = -2100;  //SYS_OPTION不存在该配置项
			
	public static final int E_DISABLE_ROLE_GROUP = -2101;  //禁止创建角色和团体
			
	public static final int E_DISABLE_CHARGE = -2102;  //禁止充值
			
	public static final int E_DISABLE_ACCESS = -2103;  //禁止访问

	public static final int E_NOT_LAST_CHARGE_DETAIL = -2010;  //不是最后一条充值注水
			
	public static final int E_CHARGE_DETAIL_CONSUMED = -2011;  //充值已消耗
			
	public static final int E_UNCHARGE_AMOUNT_ERROR = -2012;  //返充值金额错误

	public static final int E_GS_IP_NOT_EXIST = -2200;  //游戏IP信息不存在
			
	public static final int E_GS_IP_EXIST = -2201;  //游戏IP信息已存在
			
	public static final int E_APP_CONFIG_NOT_EXIST = -2202;  //游戏配置信息不存在
			
	public static final int E_APP_CONFIG_EXIST = -2203;  //游戏配置信息已存在
			
	public static final int E_CONFIG_ID_NOT_EXIST = -2204;  //配置 ID 不存在
			
	public static final int E_APP_NOT_EXIST = -2205;  //APP 信息不存在
			
	public static final int E_APP_EXIST = -2206;  //APP 信息已存在
			
	public static final int E_UNITE_DETAILID_DUP = -2301;  //合服流水号正在处理中


	//**************  begin   字符串型常量  ***************

	public static final String E_STRING_SEPARATOR = "$";    //字符串分隔符
	//**************  end   字符串型常量  ***************
}
