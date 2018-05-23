package com.linekong.erating.logic.dao;

import com.linekong.erating.api.pojo.account.JointAuthenDTO;
import com.linekong.erating.api.pojo.account.UserAuthDTO;
import com.linekong.erating.api.pojo.account.UserLogoutDTO;
import com.linekong.erating.api.pojo.response.JointAuthenRespDTO;
import com.linekong.erating.api.pojo.response.UserAuthRespDTO;
import com.linekong.erating.api.pojo.response.UserLogoutRespDTO;

public interface AccountTypeDao {
	
	 /**
     *合作运营认证服务
     * @param JointAuthenDTO jointAuthen 请求数据对象，注意TCP请求数据的格式，这里先规范对象的属性均为String
     * @return JointAuthenRespDTO  应答对象
     */
    public JointAuthenRespDTO jointAuthen(JointAuthenDTO jointAuthen);

    /**
     * 账号认证
     * @param UserAuthDTO userAuth 请求数据对象
     * @return UserAuthRespDTO 应答对象
     */
    public UserAuthRespDTO userAuth(UserAuthDTO userAuth);

    /**
     * 玩家登出服务
     * @param UserLogoutDTO userLogout 等等出请求对象
     * @return UserLogoutRespDTO 应答对象
     */
    public UserLogoutRespDTO userLogout(UserLogoutDTO userLogout);

}
