package com.linekong.erating.router.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBuferUtils {

	/**
	 * 将字节转换为String 类型
	 */
	public static String byteToString(ByteBuf byteBuf,int length) {
		byte [] b = new byte[length];
		byteBuf.readBytes(b);
		int size = returnActualLength(b);
		byte [] bt = new byte[size];
		System.arraycopy(b, 0, bt, 0,size);
		return new String(bt);
	}

	/**
	 * 将字节转换为int类型
	 */
	public static int oneByteToInt(ByteBuf byteBuf, int length) {
		byte[] b = new byte[length];
		byteBuf.readBytes(b);
		return (int) b[0];
	}

	/**
	 * int转换为byte数组
	 */
	public static byte[] intToByte(int value, int length) {
		ByteBuf b = Unpooled.buffer(length);
		b.writeInt(value);
		byte[] retByte = new byte[length];
		b.readBytes(retByte);
		return retByte;
	}

	/**
	 * 字节数组转换int
	 */
	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	// 写入字符串
	public static void writeString(ByteBuf bf, int length, String target) {
		// 准备一个临时的ByteBuf，处理字符串型的写入
		ByteBuf temp = Unpooled.buffer(length);
		// 将字符串写入临时ByteBuf中
		temp.writeBytes(target.getBytes());
		// 再写入目标ByteBuf中
		bf.writeBytes(temp, 0, length);
	}

	// 写入无符号int型
	public static void writeUnsignInt(ByteBuf bf, long value) {
		bf.writeInt((int) (value & 0xffffffff));
	}

	/**
	 * byte转换为int类型
	 * 
	 * @param byte[]
	 *            b
	 * @param int
	 *            length
	 * @return int
	 */
	public static int byteToInt(byte[] b, int length) {
		ByteBuf byteBuf = Unpooled.buffer(length);
		byteBuf.writeBytes(b);
		return byteBuf.readInt();
	}

	/**
	 * 计算实际byte长度
	 */
	public static int returnActualLength(byte[] data) {
		int i = 0;
		for (; i < data.length; i++) {
			if (data[i] == '\0')
				break;
		}
		return i;
	}

	/**
	 * 创建应答消息体的字节数组，包体的长度由应答协议而定
	 * @param int resultCode    状态码
	 * @param int bodyLength    包体的长度
	 * @return
	 */
	public static byte[] createMessageBody(int resultCode, int bodyLength) {
		ByteBuf bf = Unpooled.buffer(bodyLength);
		bf.writeInt(resultCode);
		return bf.array();
	}
}
