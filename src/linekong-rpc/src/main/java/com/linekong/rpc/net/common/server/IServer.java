package com.linekong.rpc.net.common.server;

import com.linekong.rpc.serialize.Serializer;

/**
 * Created by fangming on 2018/3/15.
 */
public abstract class IServer {

    public abstract void start(final int port, final Serializer serializer,String protocol);

    public abstract void destroy();


}
