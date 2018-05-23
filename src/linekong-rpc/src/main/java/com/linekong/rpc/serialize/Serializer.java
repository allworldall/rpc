package com.linekong.rpc.serialize;

import com.linekong.rpc.serialize.protostuff.ProtostuffSerializer;

/**
 * 序列化抽象类
 * Created by fangming on 2018/3/15.
 */
public abstract class Serializer {

    /**
     *序列化
     *@param T obj
     *@return byte[]
     */
    public abstract <T> byte[] serialize(T obj);
    /**
     * 反序列化
     * @param byte [] bytes
     * @param Class<T> clazz
     */
    public abstract <T> Object deserialize(byte[] bytes, Class<T> clazz);
    
    public static void main(String[] args) {
     	Serializer s = new ProtostuffSerializer();
     	String str = "123456";
     	s.serialize(str);
     	System.out.println(s.deserialize(s.serialize(str), String.class));
	}
    
    


}
