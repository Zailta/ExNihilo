package com.blog.application.Utility;

public class EXNAPIResponse {
	
	public String message;
	public Boolean responseFlag;
	
	public EXNAPIResponse(String message, Boolean responseFlag) {
		super();
		this.message = message;
		this.responseFlag = responseFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getResponseFlag() {
		return responseFlag;
	}

	public void setResponseFlag(Boolean responseFlag) {
		this.responseFlag = responseFlag;
	}
	

}
