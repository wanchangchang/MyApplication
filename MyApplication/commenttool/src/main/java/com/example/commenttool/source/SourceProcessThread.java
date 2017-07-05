/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.source;

import java.util.concurrent.Callable;

public class SourceProcessThread implements Callable<Object> {
	ISource mSource;

	public SourceProcessThread(ISource src) {
		mSource = src;
	}

	public Object call() {
		try {
			mSource.CallSource();
			mSource.sendMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}