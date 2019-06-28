package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private IUserService userservice;
	
	@RequestMapping("reg")
	public JsonResult doReg(@RequestParam("username")String username, 
							@RequestParam("password")String password, 
							@RequestParam("password2")String password2) {
		userservice.reg(username, password, password2);
		return new JsonResult("注册OK");
	}

	@RequestMapping("log")
	public JsonResult doLog(String username, String password, HttpSession session) {
		User user = userservice.log(username, password);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new JsonResult("登陆OK", user);
	}
	
	@RequestMapping("modifyPassword")
	public JsonResult doModifyPassword(String oldpassword,String password, String password2, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		userservice.modifyPassword(uid, oldpassword, password, password2);
		session.removeAttribute("username");
		session.removeAttribute("uid");
		return new JsonResult("修改密码OK");
	}
	
	@RequestMapping("showUserData")
	public JsonResult doShowUserData(HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		User user = userservice.showUserData(uid);
		return new JsonResult(user);
	}
	
	@RequestMapping("modifyUserData")
	public JsonResult doModifyUserData(String phone, String email, Integer gender, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		userservice.modigyUserData(uid, phone, email, gender);
		return new JsonResult("修改资料OK");
	}
}
