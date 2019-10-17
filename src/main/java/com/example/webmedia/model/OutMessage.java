package com.example.webmedia.model;

import java.util.Date;

public class OutMessage {

	private String from;
	
	private String content;

	private String status;

	private String path;

	private Date time = new Date();

	public OutMessage(){}
	
	public OutMessage(String content){
		this.content = content;
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFrom() {
		return from;
	}
	
	

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	
}
