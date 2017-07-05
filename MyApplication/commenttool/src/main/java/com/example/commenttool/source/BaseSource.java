/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.source;

import android.os.Handler;
import android.os.Message;

public class BaseSource extends BaseService implements ISource {

	private int requestID;
	private Handler handler;
	private Object resultModel;

	@Override
	public void CallSource() {
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public void setResultModel(Object resultModel) {
		this.resultModel = resultModel;
	}

	@Override
	public int getRequestID() {
		return requestID;
	}

	@Override
	public void sendMessage() {
		if(handler==null)return;
		Message msg = handler.obtainMessage(requestID);
		msg.obj = resultModel;
		handler.sendMessage(msg);
	}

}
