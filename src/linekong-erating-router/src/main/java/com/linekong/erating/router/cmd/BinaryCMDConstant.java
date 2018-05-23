package com.linekong.erating.router.cmd;

public class BinaryCMDConstant {
	
	public static final long CMD_BIND                     = 0x10000001;    //网关绑定
	
	public static final long CMD_UNBIND                   = 0x10000002;    //网关解绑
	
	public static final long CMD_HANDSET                  = 0x10001001;    //心跳消息
	
	public static final long CMD_GW_DATA_REPORT           = 0x10002003;    //网关数据上报 发送在线人数,15秒左右发送一次 混服的在线发送需要区分game_id

	public static final long CMD_CREATE_ROLE              = 0x10003102;    //创建角色
	
	public static final long CMD_ROLE_ENTER_GAME_EX5      = 0x10003119;    //角色进入游戏 检查mac是否正确:ios为idfa,其它为mac 还需要检查客户端类型,money1,money2,experience
	
	public static final long CMD_USER_BALANCE_INFO_EX1    = 0x10003411;    //用户余额查询
	
	public static final long CMD_USER_ADD_FREE_MONEY      = 0x10003412;    //代币添加 有代币的情况下必须对接,检查流水号是否符合要求,检查subject_id是否正确
	
	public static final long CMD_USER_CHAEGE_EX2          = 0x10003413;    //充值推送,具体充值数额以amount为准,需要和游戏核对，一般subject_id为5
	
	public static final long CMD_USER_IB_PAY_EX1          = 0x10003717;    //增值道具消费,检查流水号是否符合要求，检查角色等级等信息,消费时间是否正确
	
	public static final long CMD_JOINT_AUTHEN_EX          = 0x10003802;    //用户登录认证,检查mac是否正确:ios为idfa,其它为mac。 联运放OPEND_ID， TOKEN是否超出长度， IP，客户端类 型是否传递
	
	public static final long CMD_USER_LOGOUT              = 0x10003303;    //角色登出游戏 ,角色等级是否正确，金币是否传递
	
	public static final long CMD_ACTIVITY_ITEM_QUERY_EX   = 0x10003512;    //活动物品查询,活动ID是否符合规范， 角色等级是否传递
	
	public static final long CMD_USER_ITEM_MINUS_EX       = 0x10003506;    //活动物品领取
	
	public static final long CMD_USER_CASH_EXCHANGE_EX4   = 0x10003718;    //元宝流通,检查流水号是否符合要求
	
	public static final long CMD_USER_AUTH_EX4            = 0x10003313;    //用户登录认证,检查mac是否正确:ios为idfa,其它为mac
	
	
	//返回协议号
	
    public static final long CMD_BIND_RES                     = 0x20000001;    //网关绑定
	
	public static final long CMD_UNBIND_RES                   = 0x20000002;    //网关解绑
	
	public static final long CMD_HANDSET_RES                  = 0x20001001;    //心跳消息
	
	public static final long CMD_GW_DATA_REPORT_RES           = 0x20002003;    //网关数据上报 发送在线人数,15秒左右发送一次 混服的在线发送需要区分game_id

	public static final long CMD_CREATE_ROLE_RES              = 0x20003102;    //创建角色
	
	public static final long CMD_ROLE_ENTER_GAME_EX5_RES      = 0x20003119;    //角色进入游戏 检查mac是否正确:ios为idfa,其它为mac 还需要检查客户端类型,money1,money2,experience
	
	public static final long CMD_USER_BALANCE_INFO_EX1_RES    = 0x20003411;    //用户余额查询
	
	public static final long CMD_USER_ADD_FREE_MONEY_RES      = 0x20003412;    //代币添加 有代币的情况下必须对接,检查流水号是否符合要求,检查subject_id是否正确
	
	public static final long CMD_USER_CHAEGE_EX2_RES          = 0x20003413;    //充值推送,具体充值数额以amount为准,需要和游戏核对，一般subject_id为5
	
	public static final long CMD_USER_IB_PAY_EX1_RES          = 0x20003717;    //增值道具消费,检查流水号是否符合要求，检查角色等级等信息,消费时间是否正确
	
	public static final long CMD_JOINT_AUTHEN_EX_RES          = 0x20003802;    //用户登录认证,检查mac是否正确:ios为idfa,其它为mac。 联运放OPEND_ID， TOKEN是否超出长度， IP，客户端类 型是否传递
	
	public static final long CMD_USER_LOGOUT_RES              = 0x20003303;    //角色登出游戏 ,角色等级是否正确，金币是否传递
	
	public static final long CMD_ACTIVITY_ITEM_QUERY_EX_RES   = 0x20003512;    //活动物品查询,活动ID是否符合规范， 角色等级是否传递
	
	public static final long CMD_USER_ITEM_MINUS_EX_RES       = 0x20003506;    //活动物品领取
	
	public static final long CMD_USER_CASH_EXCHANGE_EX4_RES   = 0x20003718;    //元宝流通,检查流水号是否符合要求
	
	public static final long CMD_USER_AUTH_EX4_RES            = 0x20003313;    //用户登录认证,检查mac是否正确:ios为idfa,其它为mac
}
