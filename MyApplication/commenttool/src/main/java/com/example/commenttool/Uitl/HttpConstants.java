package com.example.commenttool.Uitl;

public final class HttpConstants {
	public static final int NET_START = 10000;
	public static final int NET_ERROR_IO = -1;// IO异常!
	public static final int NET_ERROR_RUN = -2;// 运行异常!
	public static final int NET_ERROR_NONET = -3;// 网络异常!
	public static final int NET_ERROR_TIMEOUT = -4;// 连接超时!
	public static final int NET_ERROR_UNKNOW = -5;// 未知异常!
	public static final int NET_ERROR_KEY_ERROR = -6;// key失效
	public static final int PARAMTER_ERROR = -99;//结果无法解析
}
