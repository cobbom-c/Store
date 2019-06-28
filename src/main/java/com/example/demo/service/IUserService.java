package com.example.demo.service;

import com.example.demo.entity.User;

public interface IUserService {
	public Integer reg(String username, String password, String password2);

	public User log(String username, String password);
	
	public Integer modifyPassword(Integer uid, String oldpassword, String password, String password2);
	
	public User showUserData(Integer uid);
	
	public Integer modigyUserData(Integer uid, String phone, String email, Integer gender);
	
	public Integer modifyAvatar(Integer uid, String avatar);
}
