/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.source;


public interface ISource {

	/**
	 * 线程执行的方法，耗时操作在此执行
	 */
	public void CallSource();

	/**
	 * 返回请求编号
	 * 
	 * @return
	 */
	public int getRequestID();

	/**
	 * 请求结束后会自动调用此方法！发handler信息在此执行
	 */
	public void sendMessage();
}
