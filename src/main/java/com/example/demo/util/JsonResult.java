package com.example.demo.util;

public class JsonResult {
	//1表示ok，0表示false
	private int state = 1;
	//状态吗对应的具体信息
	private String message = "ok";
	//业务层返回给控制层的具体数据
	private Object data;
	
	public JsonResult() {
		super();
	}
	public JsonResult(String message) {
		super();
		this.message = message;
	}
	public JsonResult(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	public JsonResult(Object data) {
		super();
		this.data = data;
	}
	public JsonResult(Throwable e) {
		super();
		this.state = 0;
		this.message = e.getMessage();
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
