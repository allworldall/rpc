package com.linekong.erating.router.dataparse;

import com.linekong.erating.api.pojo.cash.UserAddFreeMoneyDTO;
import com.linekong.erating.api.pojo.cash.UserBalanceInfoDTO;
import com.linekong.erating.api.pojo.cash.UserCashExchangeDTO;
import com.linekong.erating.api.pojo.cash.UserIBPayDTO;
import com.linekong.erating.api.pojo.response.*;
import com.linekong.erating.api.pojo.resultmapper.*;
import com.linekong.erating.router.utils.ByteBuferUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 此类用于将logic返回的对象数据装入包体中
 */
@Component
public class ResponseDataParseToByte {

    /**
     * 创建角色,应答数据装包
     * @param resp
     * @return
     */
    public byte[] getByteBody(CreateRoleRespDTO resp) {
        int length = 8;
        ByteBuf bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode());
        ByteBuferUtils.writeUnsignInt(bf, resp.getRoleId());
        return bf.array();
    }

    /**
     * 玩家免费货币添加,应答数据
     * @param userAdd
     * @param resp
     * @return
     */
    public byte[] getByteBody(UserAddFreeMoneyDTO userAdd, UserAddFreeMoneyRespDTO resp) {
        int length = 28;
        ByteBuf bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode()).writeLong(userAdd.getDetailId());
        ByteBuferUtils.writeUnsignInt(bf, userAdd.getUserId());
        ByteBuferUtils.writeUnsignInt(bf, userAdd.getRoleId());
        bf.writeInt(userAdd.getSubjectId()).writeInt(resp.getBalance());
        return bf.array();
    }

    /**
     * 活动礼品查询,应答数据
     * @param resp
     * @return
     */
    public byte[] getByteBody(ActivityItemQueryRespDTO resp) {
        int length;
        ByteBuf bf;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 124;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        int size = resp.getList().size();  //物品数
        Map<Long, Integer> map = new HashMap<Long, Integer>();//存放活动号
        Map<Long, String> mapActDesc = new HashMap<Long, String>();//存放活动描述
        Iterator<ActivityItemInfoDO> iterator1 = resp.getList().iterator();
        while(iterator1.hasNext()) {
            ActivityItemInfoDO next = iterator1.next();
            //记录每个活动Id拥有的物品种类
            if(map.get(next.getActivityId()) == null){
                map.put(next.getActivityId(), 1);
            }else {
                map.put(next.getActivityId(), map.get(next.getActivityId())+1);
            }
            mapActDesc.put(next.getActivityId(), next.getActivityDes());
        }
        int activityCount = map.size();
        resp.setActivityCount(activityCount);//活动个数
        length = 4 + 4 + size * 44 + activityCount*72;
        if(length <= 1000) {
            bf = Unpooled.buffer(length);
            //外层循环数据
            bf.writeInt(resp.getResultCode());
            bf.writeInt(activityCount);
            Iterator<Map.Entry<Long, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Integer> next = iterator.next();
                long key = next.getKey();
                int value = next.getValue();
                //一层循环数据
                ByteBuferUtils.writeUnsignInt(bf, key);
                ByteBuferUtils.writeString(bf, 64, mapActDesc.get(key));
                bf.writeInt(value);//表示该活动号下后续的物品数量
                //先获取该活动号的物品对象
                List<ActivityItemInfoDO> listItem = new ArrayList<ActivityItemInfoDO>();
                for (ActivityItemInfoDO itemInfo : resp.getList()) {
                    if (itemInfo.getActivityId() == key)
                        listItem.add(itemInfo);
                }
                //二层循环，用户写入该活动号下后续物品
                for (ActivityItemInfoDO item : listItem) {
                    ByteBuferUtils.writeString(bf, 32, item.getItemCode());
                    bf.writeInt(item.getItemNum()).writeInt((int) item.getBeginTime().getTime() / 1000)
                            .writeInt((int) item.getEndTime().getTime() / 1000);
                }
            }
            return bf.array();
        }else {  //总长度超过1000，就只发送小于1000的最大长度数据吧
            //先计算本次返回的活动和物品个数
            Iterator<Map.Entry<Long, Integer>> iterator = map.entrySet().iterator();
            List<Long> listActId = new ArrayList<>();  //存放发给客户端的所有活动号
            int bodyLength = 8;
            while (iterator.hasNext()) {
                Map.Entry<Long, Integer> next = iterator.next();
                long key = next.getKey();//活动号
                int value = next.getValue();//物品个数
                int temp = bodyLength + (72 + value * 44);
                if(temp > 1000)
                    break;
                bodyLength = temp;
                listActId.add(key);
            }
            bf = Unpooled.buffer(bodyLength);
            //外层循环数据
            bf.writeInt(resp.getResultCode());
            bf.writeInt(listActId.size());
            Iterator<Long> iteratorAct = listActId.iterator();

            while (iteratorAct.hasNext()) {
                Long actId = iteratorAct.next();
                int actNum = map.get(actId);
                //一层循环数据
                ByteBuferUtils.writeUnsignInt(bf, actId);
                ByteBuferUtils.writeString(bf, 64, mapActDesc.get(actId));
                bf.writeInt(actNum);//表示该活动号下后续的物品数量
                //先获取该活动号的物品对象
                List<ActivityItemInfoDO> listItem = new ArrayList<ActivityItemInfoDO>();
                for (ActivityItemInfoDO itemInfo : resp.getList()) {
                    if (itemInfo.getActivityId() == actId)
                        listItem.add(itemInfo);
                }
                //二层循环，用户写入该活动号下后续物品
                for (ActivityItemInfoDO item : listItem) {
                    ByteBuferUtils.writeString(bf, 32, item.getItemCode());
                    bf.writeInt(item.getItemNum()).writeInt((int) item.getBeginTime().getTime() / 1000)
                            .writeInt((int) item.getEndTime().getTime() / 1000);
                }
            }
            return bf.array();
        }

    }

    /**
     *增值道具消费应答数据装包
     * @return
     */
    public byte[] getByteBody(UserIBPayDTO userIBPay, UserIBPayRespDTO resp) {
        int length;
        ByteBuf bf;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 36;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        //变长协议的重复次数
        int balanceCount = resp.getList().size();
        length = 4 + 8 + 4 + 4 + 4 + 4 + balanceCount * 8;
        bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode()).writeLong(userIBPay.getDetailId());
        ByteBuferUtils.writeUnsignInt(bf, userIBPay.getUserId());
        bf.writeInt(userIBPay.getRatingId());
        ByteBuferUtils.writeUnsignInt(bf, resp.getCostAmount());
        bf.writeInt(balanceCount);
        for (int i=0 ; i < balanceCount; i++) {
            UserIBPayInfoDO payInfo =  resp.getList().get(i);
            bf.writeInt(payInfo.getSubjectId()).writeInt(payInfo.getBalance());
        }
        return bf.array();
    }

    /**
     * 用户余额查询应答数据装包
     * @param resp
     * @return
     */
    public byte[] getByteBody(UserBalanceInfoDTO userInfo, UserBalanceInfoRespDTO resp) {
        int length;
        ByteBuf bf;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 28;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        //变长协议的重复次数
        int subjectCount = resp.getList().size();
        length = 4 + 4 + 4 + 4 + subjectCount * 12;
        bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode());
        ByteBuferUtils.writeUnsignInt(bf, userInfo.getUserId());
        ByteBuferUtils.writeUnsignInt(bf, userInfo.getRoleId());
        bf.writeInt(subjectCount);
        for(int i = 0; i < subjectCount; i++) {
            UserBalanceInfoDO balanceInfo = resp.getList().get(i);
            bf.writeInt(balanceInfo.getSubjectId()).writeInt(balanceInfo.getTotalCharge()).writeInt(balanceInfo.getBalance());
        }
        return bf.array();
    }

    /**
     * 合作运营认证操作,应答数据装包
     * @return
     */
    public byte[] getByteBody(JointAuthenRespDTO resp) {
        int length = 284;
        ByteBuf bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode());
        ByteBuferUtils.writeUnsignInt(bf, resp.getUserId());
        bf.writeByte(resp.getUserType())
                .writeByte(resp.getAdultFlag()).writeByte(resp.getUserClass()).writeByte(resp.getUserRoleCount())
                .writeInt(resp.getUserPoint());
        ByteBuferUtils.writeUnsignInt(bf, resp.getPromoterId());
        ByteBuferUtils.writeUnsignInt(bf, resp.getUserFlag());
        bf.writeInt(resp.getCpReturnValue());
        //字符串型写入方式
        ByteBuferUtils.writeString(bf, 220, resp.getCpErrMsg());
        ByteBuferUtils.writeString(bf, 32, resp.getUserName());
        bf.writeShort(resp.getAppendix1()).writeShort(resp.getAppendix2());
        return bf.array();
    }

    /**
     * 角色进入游戏,应答数据装包
     * @return
     */
    public byte[] getByteBody(RoleEnterGameRespDTO resp) {
        int length;
        ByteBuf bf;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 16;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        //变长协议的重复次数
        int balanceCount = resp.getList().size();
        length = 4 + 4 + balanceCount * 8;
        bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode()).writeInt(balanceCount);
        for (int i = 0; i < balanceCount; i++) {
            RoleEnterGameDO enterInfo = resp.getList().get(i);
            bf.writeInt(enterInfo.getSubjectId()).writeInt(enterInfo.getAmount());
        }
        return bf.array();
    }

    /**
     * 账号认证接口,应答数据装包
     * @return
     */
    public byte[] getByteBody(UserAuthRespDTO resp) {
        int length = 24;
        ByteBuf bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode());
        ByteBuferUtils.writeUnsignInt(bf, resp.getUserID());
        bf.writeByte(resp.getUserType())
                .writeByte(resp.getAdultFlag()).writeByte(resp.getUserClass()).writeByte(resp.getPad())
                .writeInt(resp.getUserPoint());
        ByteBuferUtils.writeUnsignInt(bf, resp.getPromoterId());
        ByteBuferUtils.writeUnsignInt(bf, resp.getUserFlag());
        return bf.array();
    }

    /**
     * 玩家登出接口,应答数据装包
     * @return
     */
    public byte[] getByteBody(UserLogoutRespDTO resp) {
        ByteBuf bf;
        int length;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 16;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        //变长协议的重复次数
        int balanceCount = resp.getList().size();
        length = 4 + 4 + balanceCount * (4 + 4);
        bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode()).writeInt(balanceCount);
        for (int i = 0 ; i < balanceCount ; i++){
            UserLogoutInfoDO logouInfo = resp.getList().get(i);
            bf.writeInt(logouInfo.getSubjectId()).writeInt(logouInfo.getAmount());
        }
        return bf.array();
    }

    /**
     * 元宝流通,应答数据装包
     * @param userCashExchange
     * @param userCashExchangeResp
     * @return
     */
    public byte[] getByteBody(UserCashExchangeDTO userCashExchange, UserCashExchangeRespDTO resp) {
        int length;
        ByteBuf bf;
        //如果list为空
        if(resp.getList() == null || resp.getList().size() == 0) {
            length = 28;
            bf = Unpooled.buffer(length);
            bf.writeInt(resp.getResultCode());
            return bf.array();
        }
        //变长协议的重复次数
        int balanceCount = resp.getList().size();
        length = 4 + 8 + 4 + balanceCount * 12;
        bf = Unpooled.buffer(length);
        bf.writeInt(resp.getResultCode()).writeLong(userCashExchange.getDetailId()).writeInt(balanceCount);
        for (int i=0 ; i < balanceCount ; i++){
            UserCashExchangeDO cashExchangeInfo = resp.getList().get(i);
            bf.writeInt(cashExchangeInfo.getSubjectId()).writeInt(cashExchangeInfo.getSrcBalance()).writeInt(cashExchangeInfo.getDstBalance());
        }
        return bf.array();
    }

}
