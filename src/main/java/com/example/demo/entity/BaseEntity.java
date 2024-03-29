package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7648546300772343457L;
	
	private String createdUser;
	private Date createdTime;
	private String  modifiedUser;
	private Date modifiedTime;
	
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreateTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "BaseEntity [createUser=" + createdUser + ", createTime=" + createdTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
}
