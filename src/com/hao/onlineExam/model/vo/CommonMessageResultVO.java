package com.hao.onlineExam.model.vo;

import java.util.Map;

public class CommonMessageResultVO {
	
	private boolean success;
	private Map<String, String> messageMap;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Map<String, String> getMessageMap() {
		return messageMap;
	}
	public void setMessageMap(Map<String, String> messageMap) {
		this.messageMap = messageMap;
	}

}
