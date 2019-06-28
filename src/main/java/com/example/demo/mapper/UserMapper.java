package com.example.demo.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
	Integer addUser(User user);
	
	User findByUsername(String username);
	
	User findByUid(Integer uid);
	
	Integer updatePassword(@Param("uid")Integer uid, 
						@Param("password")String password, 
						@Param("modifiedUser")String modifiedUser, 
						@Param("modifiedTime")Date modifiedTime);
	
	Integer updateUserData(@Param("uid")Integer uid,
						@Param("phone")String phone,
						@Param("email")String email,
						@Param("gender")Integer gender);
}
