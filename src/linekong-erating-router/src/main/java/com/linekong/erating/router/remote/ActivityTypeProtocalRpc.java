package com.linekong.erating.router.remote;

import com.linekong.erating.api.pojo.activity.ActivityItemQueryDTO;
import com.linekong.erating.api.pojo.activity.UserItemMinusDTO;
import com.linekong.erating.api.pojo.response.ActivityItemQueryRespDTO;
import com.linekong.erating.api.remote.ActivityTypeProtocalInterface;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.dataparse.RequestDataParseToObject;
import com.linekong.erating.router.dataparse.ResponseDataParseToByte;
import com.linekong.erating.router.utils.ByteBuferUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 此类用于通过rpc调用活动服务
 */
@Service
public class ActivityTypeProtocalRpc extends BaseBinaryProtocol{

    private static final Logger log = Logger.getLogger(ActivityTypeProtocalRpc.class);

    @Autowired
    private RequestDataParseToObject dataToObject;

    @Autowired
    private ResponseDataParseToByte dataToByte;

    @Autowired
    private ActivityTypeProtocalInterface activityInterface;

    /**
     * 活动物品查询
     * @param msg
     * @return
     */
    public PDUMessage activityItemQuery(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、封装调用礼品查询服务方法的参数
        ActivityItemQueryDTO activityItemQuery = dataToObject.activityItemQueryDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("activityItemQuery:" + activityItemQuery.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_ACTIVITY_ITEM_QUERY_EX_RES, 126);//checksum值校验不通过
        }
        //2、rpc调用
        ActivityItemQueryRespDTO itemQueryResp = activityInterface.activityItemQuery(activityItemQuery);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_ACTIVITY_ITEM_QUERY_EX_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(itemQueryResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("activityItemQuery:" + activityItemQuery.toString() + "|checkSum=" + message.getCheckSum() + "|result itemQueryResp: " + itemQueryResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 活动礼品领取
     * @param msg
     * @return
     */
    public PDUMessage userItemMinus(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //1、获取调用活动礼品领取服务方法的参数
        UserItemMinusDTO userItemMinus = dataToObject.userItemMinusDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userItemMinus:" + userItemMinus.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_ITEM_MINUS_EX_RES, 4);//checksum值校验不通过
        }
        //2、rpc调用
        int result = activityInterface.userItemMinus(userItemMinus);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_ITEM_MINUS_EX_RES);
        message.setHeader(msg.getHeader());
        message.setBody(ByteBuferUtils.intToByte(result, 4));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userItemMinus:" + userItemMinus.toString() + "|checkSum=" + message.getCheckSum() + "|result = " + result + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }
}
