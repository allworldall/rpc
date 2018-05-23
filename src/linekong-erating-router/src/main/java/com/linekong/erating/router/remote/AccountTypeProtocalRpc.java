package com.linekong.erating.router.remote;

import com.linekong.erating.api.pojo.account.JointAuthenDTO;
import com.linekong.erating.api.pojo.account.UserAuthDTO;
import com.linekong.erating.api.pojo.account.UserLogoutDTO;
import com.linekong.erating.api.pojo.response.JointAuthenRespDTO;
import com.linekong.erating.api.pojo.response.UserAuthRespDTO;
import com.linekong.erating.api.pojo.response.UserLogoutRespDTO;
import com.linekong.erating.api.remote.AccountTypeProtocolInterface;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.dataparse.RequestDataParseToObject;
import com.linekong.erating.router.dataparse.ResponseDataParseToByte;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *此类用于调用rpc账号服务
 */

@Service
public class AccountTypeProtocalRpc extends BaseBinaryProtocol {

    private static final Logger log = Logger.getLogger(AccountTypeProtocalRpc.class);
    @Autowired
    private RequestDataParseToObject dataToObject;

    @Autowired
    private ResponseDataParseToByte dataToByte;

    @Autowired
    private AccountTypeProtocolInterface acountInterface;

    /**
     * 合作运营认证服务
     * @param msg
     * @return
     */
    public PDUMessage jointAuthen(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用rpc方法的参数
        JointAuthenDTO jointAuthen = dataToObject.jointAuthenDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("jointAuthen:" + jointAuthen.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_JOINT_AUTHEN_EX_RES, 284);//checksum值校验不通过
        }
        //2、rpc调用
        JointAuthenRespDTO jointAuthenResp = acountInterface.jointAuthen(jointAuthen);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_JOINT_AUTHEN_EX_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(jointAuthenResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("jointAuthen:" + jointAuthen.toString() + "|checkSum=" + message.getCheckSum() + "|result jointAuthenResp: " + jointAuthenResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 账号认证
     * @param msg
     * @return
     */
    public PDUMessage userAuth(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用rpc方法的参数
        UserAuthDTO userAuth = dataToObject.userAuthDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userAuth:" + userAuth.toString() + "|checkSum error " + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_AUTH_EX4_RES, 24);//checksum值校验不通过
        }
        //调用rpc方法
        UserAuthRespDTO userAuthResp = acountInterface.userAuth(userAuth);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_AUTH_EX4_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userAuthResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userAuth:" + userAuth.toString() + "|checkSum=" + message.getCheckSum() + "|result userAuthResp: " + userAuthResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 玩家登出
     * @param msg
     * @return
     */
    public PDUMessage userLogout(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用rpc方法的参数
        UserLogoutDTO userLogout = dataToObject.userLogoutDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userLogout:" + userLogout.toString() + "|checkSum error " + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_LOGOUT_RES, 16);//checksum值校验不通过
        }
        //2、调用rpc方法
        UserLogoutRespDTO userLogoutResp = acountInterface.userLogout(userLogout);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_LOGOUT_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userLogoutResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userLogout:" + userLogout.toString() + "|checkSum=" + message.getCheckSum() + "|result userLogoutResp: " + userLogoutResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }
}
