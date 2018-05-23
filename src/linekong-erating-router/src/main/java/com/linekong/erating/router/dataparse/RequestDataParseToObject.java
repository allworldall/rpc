package com.linekong.erating.router.dataparse;

import com.linekong.erating.api.pojo.account.JointAuthenDTO;
import com.linekong.erating.api.pojo.account.UserAuthDTO;
import com.linekong.erating.api.pojo.account.UserLogoutDTO;
import com.linekong.erating.api.pojo.activity.ActivityItemQueryDTO;
import com.linekong.erating.api.pojo.activity.UserItemMinusDTO;
import com.linekong.erating.api.pojo.cash.*;
import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;
import com.linekong.erating.router.utils.ByteBuferUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.net.common.codec.binary.PDUMessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Component;

/**
 * 此类用于将PDUMessage的body数据拆包后封装成对象，传入logic
 */
@Component
public class RequestDataParseToObject {

    //**************begin   账号类的对象封装      ***********************************
    /**
     * 合作运营认证服务请求rpc参数封装
     *
     * @param msg
     * @return
     */
    public JointAuthenDTO jointAuthenDataParse (PDUMessage msg) {
        JointAuthenDTO jointAuthen = new JointAuthenDTO();
        PDUMessageHeader header = msg.getHeader();
        String data = new String(msg.getBody());
        jointAuthen.setTotalLength(header.getTotalLength());
        jointAuthen.setVersion(header.getVersion());
        jointAuthen.setRemainPackages(header.getRemainPackages());
        jointAuthen.setCommandId(header.getCommandId());
        jointAuthen.setSequenceId(header.getSequenceId());
        jointAuthen.setGameId(header.getGameId());
        jointAuthen.setGatewayId(header.getGatewayId());
        jointAuthen.setUn(data.substring(data.indexOf('>') + 1, data.indexOf("<Token>")));
        jointAuthen.setToken(data.substring(data.indexOf("<Token>") + 7, data.indexOf("<UserIP4>")));
        jointAuthen.setUserIP4(Long.parseLong(data.substring(data.indexOf("<UserIP4>") + 9, data.indexOf("<Port>"))));
        jointAuthen.setPort(data.substring(data.indexOf("<Port>") + 6, data.indexOf("<MAC>")));
        jointAuthen.setMac(data.substring(data.indexOf("<MAC>") + 5, data.indexOf("<ClientType>")));
        jointAuthen.setClientType(data.substring(data.indexOf("<ClientType>") + 12, data.indexOf("<SdkVersion>")));
        jointAuthen.setSdkVersion(data.substring(data.indexOf("<SdkVersion>") + 12, data.indexOf("<UnixTime>")));
        jointAuthen.setUnixTime(data.substring(data.indexOf("<UnixTime>") + 10, data.indexOf("<CP_ID>")));
        jointAuthen.setCpId(data.substring(data.indexOf("<CP_ID>") + 7, data.indexOf("<Pad>")));
        jointAuthen.setPad(data.substring(data.indexOf("<Pad>") + 5, data.indexOf("<ADID>")));
        jointAuthen.setAdId(data.substring(data.indexOf("<ADID>") + 6, data.indexOf("<UID>")));
        jointAuthen.setUId(data.substring(data.indexOf("<UID>") + 5, data.indexOf("<AdultState>")));
        jointAuthen.setAdultState(data.substring(data.indexOf("<AdultState>") + 12, data.indexOf("<Password>")));
        jointAuthen.setPassword(data.substring(data.indexOf("<Password>") + 10, data.indexOf("<PasswordType>")));
        jointAuthen.setPasswordType(data.substring(data.indexOf("<PasswordType>") + 14, data.indexOf("<IDCode>")));
        jointAuthen.setIdCode(data.substring(data.indexOf("<IDCode>") + 8));
        return jointAuthen;
    }

    /**
     * 账号认证服务请求rpc参数封装
     *
     * @param msg
     * @return
     */
    public UserAuthDTO userAuthDataParse(PDUMessage msg) {
        UserAuthDTO userAuth = new UserAuthDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userAuth.setTotalLength(header.getTotalLength());
        userAuth.setVersion(header.getVersion());
        userAuth.setRemainPackages(header.getRemainPackages());
        userAuth.setCommandId(header.getCommandId());
        userAuth.setSequenceId(header.getSequenceId());
        userAuth.setGameId(header.getGameId());
        userAuth.setGatewayId(header.getGatewayId());
        userAuth.setUserName(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setPassword(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setPasswordType(byteBuf.readByte());
        userAuth.setGameClientType(byteBuf.readByte());
        userAuth.setUserPort(byteBuf.readUnsignedShort());
        userAuth.setUserIP(byteBuf.readUnsignedInt());
        userAuth.setMatrixPassword(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setMatrixCoord(ByteBuferUtils.byteToString(byteBuf, 16));
        userAuth.setMac(ByteBuferUtils.byteToString(byteBuf, 256));
        userAuth.setAdId(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setHardwareSN1(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setHardwareSN2(ByteBuferUtils.byteToString(byteBuf, 32));
        userAuth.setUddi(ByteBuferUtils.byteToString(byteBuf, 64));
        return userAuth;
    }

    /**
     * 玩家登出服务请求rpc参数封装
     *
     * @param msg
     * @return
     */
    public UserLogoutDTO userLogoutDataParse (PDUMessage msg) {
        UserLogoutDTO userLogout = new UserLogoutDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userLogout.setTotalLength(header.getTotalLength());
        userLogout.setVersion(header.getVersion());
        userLogout.setRemainPackages(header.getRemainPackages());
        userLogout.setCommandId(header.getCommandId());
        userLogout.setSequenceId(header.getSequenceId());
        userLogout.setGameId(header.getGameId());
        userLogout.setGatewayId(header.getGatewayId());
        userLogout.setUserId(byteBuf.readUnsignedInt());
        userLogout.setRoleId(byteBuf.readUnsignedInt());
        userLogout.setLogoutFlag(byteBuf.readUnsignedByte());
        userLogout.setRoleOccupation(byteBuf.readUnsignedByte());
        userLogout.setRoleLevel(byteBuf.readUnsignedShort());
        userLogout.setRatingId(byteBuf.readInt());
        userLogout.setMoney1(byteBuf.readUnsignedInt());
        userLogout.setMoney2(byteBuf.readUnsignedInt());
        userLogout.setExperience(byteBuf.readLong());
        return userLogout;
    }
    //**************end   账号类的对象封装      ************************************


    //**************begin   活动类的对象封装      ***********************************
    /**
     * 活动物品查询服务,请求rpc参数封装
     *
     * @param msg
     * @return
     */
    public ActivityItemQueryDTO activityItemQueryDataParse (PDUMessage msg) {
        ActivityItemQueryDTO activityItemQuery = new ActivityItemQueryDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        activityItemQuery.setTotalLength(header.getTotalLength());
        activityItemQuery.setVersion(header.getVersion());
        activityItemQuery.setRemainPackages(header.getRemainPackages());
        activityItemQuery.setCommandId(header.getCommandId());
        activityItemQuery.setSequenceId(header.getSequenceId());
        activityItemQuery.setGameId(header.getGameId());
        activityItemQuery.setGatewayId(header.getGatewayId());
        activityItemQuery.setUserId(byteBuf.readUnsignedInt());
        activityItemQuery.setRoleId(byteBuf.readUnsignedInt());
        activityItemQuery.setActivityId(byteBuf.readUnsignedInt());
        activityItemQuery.setRoleLevel(byteBuf.readUnsignedShort());
        activityItemQuery.setPad1(byteBuf.readUnsignedShort());
        activityItemQuery.setPad2(byteBuf.readUnsignedInt());
        return activityItemQuery;
    }

    /**
     * 活动礼品领取,请求rpc参数封装
     *
     * @param msg
     * @return
     */
    public UserItemMinusDTO userItemMinusDataParse (PDUMessage msg) {
        UserItemMinusDTO userItemMinus = new UserItemMinusDTO();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        PDUMessageHeader header = msg.getHeader();
        byteBuf.writeBytes(msg.getBody());
        userItemMinus.setTotalLength(header.getTotalLength());
        userItemMinus.setVersion(header.getVersion());
        userItemMinus.setRemainPackages(header.getRemainPackages());
        userItemMinus.setCommandId(header.getCommandId());
        userItemMinus.setSequenceId(header.getSequenceId());
        userItemMinus.setGameId(header.getGameId());
        userItemMinus.setGatewayId(header.getGatewayId());
        userItemMinus.setUserId(byteBuf.readUnsignedInt());
        userItemMinus.setRoleId(byteBuf.readUnsignedInt());
        int itemCount = byteBuf.readInt();
        userItemMinus.setItemCount(itemCount);
        long[] activityId = new long[itemCount];
        String[] itemCode = new String[itemCount];
        int[] actitemNumivityId = new int[itemCount];
        //注意变长包的对象封装
        for(int i = 0 ; i < itemCount ;i++) {
            activityId[i] = byteBuf.readUnsignedInt();
            itemCode[i] = ByteBuferUtils.byteToString(byteBuf, 32);
            actitemNumivityId[i] = byteBuf.readInt();
        }
        userItemMinus.setActivityId(activityId);
        userItemMinus.setItemCode(itemCode);
        userItemMinus.setItemNum(actitemNumivityId);
        return userItemMinus;
    }
    //**************end   活动类的对象封装       ***********************************


    //**************begin   货币服务类的对象封装      ***********************************
    /**
     * 玩家免费货币添加,请求rpc参数封装
     * @param msg
     * @return
     */
    public UserAddFreeMoneyDTO userAddFreeMoneyDataParse (PDUMessage msg) {
        UserAddFreeMoneyDTO userAddFreeMoney = new UserAddFreeMoneyDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userAddFreeMoney.setTotalLength(header.getTotalLength());
        userAddFreeMoney.setVersion(header.getVersion());
        userAddFreeMoney.setRemainPackages(header.getRemainPackages());
        userAddFreeMoney.setCommandId(header.getCommandId());
        userAddFreeMoney.setSequenceId(header.getSequenceId());
        userAddFreeMoney.setGameId(header.getGameId());
        userAddFreeMoney.setGatewayId(header.getGatewayId());
        userAddFreeMoney.setDetailId(byteBuf.readLong());
        userAddFreeMoney.setUserId(byteBuf.readUnsignedInt());
        userAddFreeMoney.setRoleId(byteBuf.readUnsignedInt());
        userAddFreeMoney.setSubjectId(byteBuf.readInt());
        userAddFreeMoney.setAmount(byteBuf.readInt());
        userAddFreeMoney.setAddTime(byteBuf.readInt());
        userAddFreeMoney.setSource(ByteBuferUtils.byteToString(byteBuf, 32));
        return userAddFreeMoney;
    }

    /**
     * 用户余额查询,请求rpc参数封装
     * @param msg
     * @return
     */
    public UserBalanceInfoDTO userBalanceInfoDataParse (PDUMessage msg) {
        UserBalanceInfoDTO userBalanceInfo = new UserBalanceInfoDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userBalanceInfo.setTotalLength(header.getTotalLength());
        userBalanceInfo.setVersion(header.getVersion());
        userBalanceInfo.setRemainPackages(header.getRemainPackages());
        userBalanceInfo.setCommandId(header.getCommandId());
        userBalanceInfo.setSequenceId(header.getSequenceId());
        userBalanceInfo.setGameId(header.getGameId());
        userBalanceInfo.setGatewayId(header.getGatewayId());
        userBalanceInfo.setUserId(byteBuf.readUnsignedInt());
        userBalanceInfo.setRoleId(byteBuf.readUnsignedInt());
        userBalanceInfo.setRatingId(byteBuf.readInt());
        return userBalanceInfo;
    }

    /**
     * 元宝流通,请求rpc参数封装
     * @param msg
     * @return
     */
    public UserCashExchangeDTO userCashExchangeDataParse (PDUMessage msg) {
        UserCashExchangeDTO userCashExchange = new UserCashExchangeDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userCashExchange.setTotalLength(header.getTotalLength());
        userCashExchange.setVersion(header.getVersion());
        userCashExchange.setRemainPackages(header.getRemainPackages());
        userCashExchange.setCommandId(header.getCommandId());
        userCashExchange.setSequenceId(header.getSequenceId());
        userCashExchange.setGameId(header.getGameId());
        userCashExchange.setGatewayId(header.getGatewayId());
        userCashExchange.setDetailId(byteBuf.readLong());
        userCashExchange.setSrcRoleId(byteBuf.readUnsignedInt());
        userCashExchange.setDstRoleId(byteBuf.readUnsignedInt());
        userCashExchange.setExchangeTime(byteBuf.readUnsignedInt());
        userCashExchange.setType(byteBuf.readInt());
        userCashExchange.setSrcGameId(byteBuf.readInt());
        userCashExchange.setDstGameId(byteBuf.readInt());
        userCashExchange.setItemCode(ByteBuferUtils.byteToString(byteBuf, 32));
        userCashExchange.setItemNum(byteBuf.readInt());
        int subjectCount = byteBuf.readInt();
        userCashExchange.setSubjectCount(subjectCount);
        int[] subjectId = new int[subjectCount];
        long[] subAmount = new long[subjectCount];
        for (int i=0 ; i<subjectCount ; i++){
            subjectId[i] = byteBuf.readInt();
            subAmount[i] = byteBuf.readUnsignedInt();
        }
        userCashExchange.setSubjectId(subjectId);
        userCashExchange.setSubAmount(subAmount);
        return userCashExchange;
    }

    /**
     * 玩家充值,请求rpc参数封装
     * @param msg
     * @return
     */
    public UserChargeDTO userChargeDataParse (PDUMessage msg) {
        UserChargeDTO userCharge = new UserChargeDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userCharge.setTotalLength(header.getTotalLength());
        userCharge.setVersion(header.getVersion());
        userCharge.setRemainPackages(header.getRemainPackages());
        userCharge.setCommandId(header.getCommandId());
        userCharge.setSequenceId(header.getSequenceId());
        userCharge.setGameId(header.getGameId());
        userCharge.setGatewayId(header.getGatewayId());
        userCharge.setDetailId(byteBuf.readLong());
        userCharge.setUserId(byteBuf.readUnsignedInt());
        userCharge.setSubjectId(byteBuf.readUnsignedShort());
        userCharge.setPad(byteBuf.readShort());
        userCharge.setAmount(byteBuf.readInt());
        userCharge.setPad2(byteBuf.readInt());
        userCharge.setChargeTime(byteBuf.readUnsignedInt());
        userCharge.setTotalAmount(byteBuf.readInt());
        userCharge.setBalance(byteBuf.readInt());
        userCharge.setAttachCode(ByteBuferUtils.byteToString(byteBuf, 64));
        return userCharge;
    }

    /**
     * 增值道具消费,请求rpc参数封装
     * @param msg
     * @return
     */
    public UserIBPayDTO userIBPayDataParse (PDUMessage msg) {
        UserIBPayDTO userIBPay = new UserIBPayDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        userIBPay.setTotalLength(header.getTotalLength());
        userIBPay.setVersion(header.getVersion());
        userIBPay.setRemainPackages(header.getRemainPackages());
        userIBPay.setCommandId(header.getCommandId());
        userIBPay.setSequenceId(header.getSequenceId());
        userIBPay.setGameId(header.getGameId());
        userIBPay.setGatewayId(header.getGatewayId());
        userIBPay.setDetailId(byteBuf.readLong());
        userIBPay.setUserId(byteBuf.readUnsignedInt());
        userIBPay.setRoleId(byteBuf.readUnsignedInt());
        userIBPay.setRoleGender(byteBuf.readUnsignedByte());
        userIBPay.setRoleOccupation(byteBuf.readUnsignedByte());
        userIBPay.setRoleLevel(byteBuf.readUnsignedShort());
        userIBPay.setRatingId(byteBuf.readInt());
        userIBPay.setIBCode(ByteBuferUtils.byteToString(byteBuf, 32));
        userIBPay.setPackageFlag(byteBuf.readUnsignedByte());
        userIBPay.setPad1(byteBuf.readUnsignedByte());
        userIBPay.setCount(byteBuf.readUnsignedShort());
        userIBPay.setPayTime(byteBuf.readInt());
        userIBPay.setUserIP(byteBuf.readUnsignedInt());
        userIBPay.setPrice(byteBuf.readInt());
        userIBPay.setDiscountPrice(byteBuf.readInt());
        int subjCount = byteBuf.readInt();
        int[] subjId = new int[subjCount];
        long[] subAmount = new long[subjCount];
        userIBPay.setSubjectCount(subjCount);
        for (int i = 0; i< subjCount; i++){
            subjId[i] = byteBuf.readInt();
            subAmount[i] = byteBuf.readUnsignedInt();
        }
        userIBPay.setSubjectId(subjId);
        userIBPay.setSubAmount(subAmount);
        return userIBPay;
    }
    //**************end   货币服务类的对象封装      ***********************************


    //**************begin   网关服务类的对象封装      ***********************************
    /**
     * 网关绑定,请求rpc参数封装
     * @param msg
     * @return
     */

    public GatewayBindDTO gatewayBindDataParse(PDUMessage msg) {
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        GatewayBindDTO gatewayBind = new GatewayBindDTO();
        gatewayBind.setTotalLength(header.getTotalLength());
        gatewayBind.setVersion(header.getVersion());
        gatewayBind.setRemainPackages(header.getRemainPackages());
        gatewayBind.setCommandId(header.getCommandId());
        gatewayBind.setSequenceId(header.getSequenceId());
        gatewayBind.setGameId(header.getGameId());
        gatewayBind.setGatewayId(header.getGatewayId());
        //包体数据
        gatewayBind.setGatewayCode(ByteBuferUtils.byteToString(byteBuf, 32));
        gatewayBind.setGatewayPassword(ByteBuferUtils.byteToString(byteBuf, 32));
        gatewayBind.setMac(ByteBuferUtils.byteToString(byteBuf, 12));
        gatewayBind.setReconnectFlag(ByteBuferUtils.oneByteToInt(byteBuf, 1));
        gatewayBind.setPad(ByteBuferUtils.oneByteToInt(byteBuf, 1));
        gatewayBind.setPad1(byteBuf.readShort());
        gatewayBind.setServerId(byteBuf.readInt());

        return gatewayBind;
    }

    /**
     * 网关解绑,请求rpc参数封装
     * @param msg
     * @return
     */

    public GatewayUnBindDTO gatewayUnBindDataParse(PDUMessage msg) {
        PDUMessageHeader header = msg.getHeader();
        GatewayUnBindDTO gatewayUnBind = new GatewayUnBindDTO();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        gatewayUnBind.setTotalLength(header.getTotalLength());
        gatewayUnBind.setVersion(header.getVersion());
        gatewayUnBind.setRemainPackages(header.getRemainPackages());
        gatewayUnBind.setCommandId(header.getCommandId());
        gatewayUnBind.setSequenceId(header.getSequenceId());
        gatewayUnBind.setGameId(header.getGameId());
        gatewayUnBind.setGatewayId(header.getGatewayId());
        //包体数据
        gatewayUnBind.setServerId(byteBuf.readInt());//包体的区服id
        return gatewayUnBind;
    }

    /**
     * 网关数据信息上报,请求rpc参数封装
     * @param msg
     * @return
     */

    public GatewayDataReportDTO gatewayDataReportDataParse(PDUMessage msg) {
        PDUMessageHeader header = msg.getHeader();
        GatewayDataReportDTO gatewayDataReport = new GatewayDataReportDTO();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        gatewayDataReport.setTotalLength(header.getTotalLength());
        gatewayDataReport.setVersion(header.getVersion());
        gatewayDataReport.setRemainPackages(header.getRemainPackages());
        gatewayDataReport.setCommandId(header.getCommandId());
        gatewayDataReport.setSequenceId(header.getSequenceId());
        gatewayDataReport.setGameId(header.getGameId());
        gatewayDataReport.setGatewayId(header.getGatewayId());
        //包体数据
        gatewayDataReport.setServerId(byteBuf.readInt());
        int dataCount = byteBuf.readInt();
        gatewayDataReport.setDataCount(dataCount);
        int[] dataTpe = new int[dataCount];
        int[] dataValue = new int[dataCount];
        for(int i=0 ; i<dataCount ; i++){
            dataTpe[i] = byteBuf.readInt();
            dataValue[i] = byteBuf.readInt();
        }
        gatewayDataReport.setDataType(dataTpe);
        gatewayDataReport.setDataValue(dataValue);
        return gatewayDataReport;
    }
    //**************end   网关服务类的对象封装      ***********************************


    //**************begin   角色服务类的对象封装      ***********************************
    /**
     * 创建新角色，请求rpc参数封装
     * @param msg
     * @return
     */
    public CreateRoleDTO createRoleDataParse (PDUMessage msg) {
        CreateRoleDTO createRole = new CreateRoleDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        createRole.setTotalLength(header.getTotalLength());
        createRole.setVersion(header.getVersion());
        createRole.setRemainPackages(header.getRemainPackages());
        createRole.setCommandId(header.getCommandId());
        createRole.setSequenceId(header.getSequenceId());
        createRole.setGameId(header.getGameId());
        createRole.setGatewayId(header.getGatewayId());
        createRole.setUserId(byteBuf.readUnsignedInt());
        createRole.setRoleName(ByteBuferUtils.byteToString(byteBuf, 32));
        createRole.setRoleGender(byteBuf.readUnsignedByte());
        createRole.setRoleOccupation(byteBuf.readUnsignedByte());
        createRole.setInitialLevel(byteBuf.readUnsignedShort());
        createRole.setUserIP(byteBuf.readUnsignedInt());
        createRole.setUserPort(byteBuf.readUnsignedShort());
        createRole.setCommunityId(byteBuf.readUnsignedByte());
        createRole.setPad1(byteBuf.readUnsignedByte());
        return createRole;
    }

    /**
     * 角色进入游戏,请求rpc参数封装
     * @param msg
     * @return
     */
    public RoleEnterGameDTO roleEnterGameDataParse (PDUMessage msg) {
        RoleEnterGameDTO roleEnterGame = new RoleEnterGameDTO();
        PDUMessageHeader header = msg.getHeader();
        ByteBuf byteBuf = Unpooled.buffer(msg.getBody().length);
        byteBuf.writeBytes(msg.getBody());
        roleEnterGame.setTotalLength(header.getTotalLength());
        roleEnterGame.setVersion(header.getVersion());
        roleEnterGame.setRemainPackages(header.getRemainPackages());
        roleEnterGame.setCommandId(header.getCommandId());
        roleEnterGame.setSequenceId(header.getSequenceId());
        roleEnterGame.setGameId(header.getGameId());
        roleEnterGame.setGatewayId(header.getGatewayId());
        roleEnterGame.setServerId(byteBuf.readInt());
        roleEnterGame.setUserId(byteBuf.readUnsignedInt());
        roleEnterGame.setRoleId(byteBuf.readUnsignedInt());
        roleEnterGame.setLevel(byteBuf.readUnsignedShort());
        roleEnterGame.setGender(byteBuf.readUnsignedByte());
        roleEnterGame.setOccupationId(byteBuf.readUnsignedByte());
        roleEnterGame.setCorpsId(byteBuf.readUnsignedInt());
        roleEnterGame.setCommunityId(byteBuf.readUnsignedShort());
        roleEnterGame.setClientPort(byteBuf.readUnsignedShort());
        roleEnterGame.setClientIP(byteBuf.readUnsignedInt());
        roleEnterGame.setClientType(byteBuf.readInt());
        roleEnterGame.setClientMAC(ByteBuferUtils.byteToString(byteBuf, 256));
        roleEnterGame.setHardwareSN1(ByteBuferUtils.byteToString(byteBuf, 32));
        roleEnterGame.setHardwareSN2(ByteBuferUtils.byteToString(byteBuf, 32));
        roleEnterGame.setUddi(ByteBuferUtils.byteToString(byteBuf, 64));
        roleEnterGame.setModelVersion(ByteBuferUtils.byteToString(byteBuf, 128));
        roleEnterGame.setLdId(ByteBuferUtils.byteToString(byteBuf, 256));
        return roleEnterGame;
    }
    //**************end   角色服务类的对象封装      ***********************************
}
