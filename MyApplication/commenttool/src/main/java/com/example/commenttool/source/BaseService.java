/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.source;


import com.example.commenttool.Model.BaseResultModel;
import com.example.commenttool.Uitl.GsonUtil;
import com.example.commenttool.Uitl.HttpUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseService {
	public static final String ANALYZE_ERROR="jason解析失败";
	private static final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(0, 5, 60L, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(30));
	/**
	 * GET方式全参数传递
	 * 
	 * @param url
	 * @param clzss
	 * @return
	 */
	protected static <T extends BaseResultModel> T getUrl(String url, Class<T> clzss) {
		try {
			String data = HttpUtil.doGet(url);
			return (T) GsonUtil.jsonToObj(data, clzss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GsonUtil.jsonToObj(ANALYZE_ERROR, clzss);
	}

//	/**
//	 * POST方式
//	 *
//	 * @param url
//	 * @param content
//	 * @param clzss
//	 * @return
//	 */
//	protected static <T extends BaseResultModel> T postUrlNotEncypt(String url, String content, Class<T> clzss) {
//		try {
//			String data = HttpUtil.doPost(url, content);
//			return (T) GsonUtil.jsonToObj(data, clzss);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return GsonUtil.jsonToObj(ANALYZE_ERROR, clzss);
//	}

	/**
	 * Post方式
	 *
	 * @param url
	 * @param clzss
	 * @return
	 */
	protected static <T extends BaseResultModel> T postUrl(String url, Class<T> clzss) {
		try {
			String data = HttpUtil.doPostAndEncypt(url);
			return (T) GsonUtil.jsonToObj(data, clzss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GsonUtil.jsonToObj(ANALYZE_ERROR, clzss);
	}
//
//	/**
//	 * POST方式，只加URL参数
//	 *
//	 * @param url
//	 * @param clzss
//	 * @return
//	 */
//	protected static <T extends BaseResultModel> T postUrl(String url, Class<T> clzss) {
//		return postUrl(url, null, clzss);
//	}

	static public Future<Object> dispatch(ISource src) {
		Callable<Object> thread = new SourceProcessThread(src);
		Future<Object> futrue = null;
		try {
			futrue = mThreadPool.submit(thread);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		thread = null;
		return futrue;
	}
}
