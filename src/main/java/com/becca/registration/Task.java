package com.becca.registration;

import java.util.UUID;

public class Task {
	private String title;
	private String date;
	private String content;
	private UUID id;

	public Task() {
		
	}
	
	public Task(String title, String date, String content) {
		super();
		this.id = UUID.randomUUID();
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
