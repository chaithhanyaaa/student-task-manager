package com.studenttaskmanager.model;

import java.time.LocalDateTime;

public class Task {
	private int id;
	private String title;
	private LocalDateTime createdAt;
	
	public Task()
	{
		
	}
	
	public Task(String title,LocalDateTime createdAt)
	{
		this.title=title;
		this.createdAt=createdAt;
	}
	
	public Task(Integer id, String title,LocalDateTime createdAt)
	{
		this.id=id;
		this.title=title;
		this.createdAt=createdAt;
	}
	
	 public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }
	

}
