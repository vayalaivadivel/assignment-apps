package org.dbs.service.three.model;

public class GreetingResponse {
	private String statusMessage = "SUCCESS";
	private int statusCode = 200;
	private String requestUri;
	private Object data;

	
	public GreetingResponse(String requestUri, Object data) {
		super();
		this.requestUri = requestUri;
		this.data = data;
	} 

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

}
