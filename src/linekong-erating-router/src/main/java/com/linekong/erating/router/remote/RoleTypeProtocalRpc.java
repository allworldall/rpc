package com.linekong.erating.router.remote;

import com.linekong.erating.api.pojo.response.CreateRoleRespDTO;
import com.linekong.erating.api.pojo.response.RoleEnterGameRespDTO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;
import com.linekong.erating.api.remote.RoleTypeProtocolInterface;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.dataparse.RequestDataParseToObject;
import com.linekong.erating.router.dataparse.ResponseDataParseToByte;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 此类用于调用rpc角色服务
 */
@Service
public class RoleTypeProtocalRpc extends BaseBinaryProtocol {

    private static final Logger log = Logger.getLogger(GatewayTypeProtocolRpc.class);

    @Autowired
    private RequestDataParseToObject dataToObject;

    @Autowired
    private ResponseDataParseToByte dataToByte;

    @Autowired
    private RoleTypeProtocolInterface roleInterface;

    /**
     * 创建新角色
     * @param msg
     * @return
     */
    public PDUMessage createRole(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用rpc方法的参数
        CreateRoleDTO createRole = dataToObject.createRoleDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("createRole:" + createRole.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_CREATE_ROLE_RES, 8);//checksum值校验不通过
        }
        //调用rpc方法
        CreateRoleRespDTO roleResp = roleInterface.createRole(createRole);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_CREATE_ROLE_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(roleResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("createRole:" + createRole.toString() + "|checkSum=" + message.getCheckSum() + "|result roleResp: " + roleResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 角色进入游戏
     * @param msg
     * @return
     */
    public PDUMessage roleEnterGame(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用rpc方法的参数
        RoleEnterGameDTO roleEnterGame = dataToObject.roleEnterGameDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("roleEnterGame:" + roleEnterGame.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_ROLE_ENTER_GAME_EX5_RES, 16);//checksum值校验不通过
        }
        //调用rpc方法
        RoleEnterGameRespDTO roleEnterGameResp = roleInterface.roleEnterGame(roleEnterGame);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_ROLE_ENTER_GAME_EX5_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(roleEnterGameResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("roleEnterGame:" + roleEnterGame.toString() + "|checkSum=" + message.getCheckSum() + "|result roleEnterGameResp: " + roleEnterGameResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }
}
