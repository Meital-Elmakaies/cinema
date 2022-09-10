package com.hit.server;

public class Request {
	private String action;
	private String body;
	
	public Request(String action,String body) {
		this.body=body;
		this.action=action;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
