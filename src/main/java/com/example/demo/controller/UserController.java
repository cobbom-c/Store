package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	private static final long UPLOAD_MAX_SIZE = 3*1024*1024;
	private static final List<String> UPLOAD_CONTENT_TYPES = new ArrayList<>();
	static {
		UPLOAD_CONTENT_TYPES.add("image/jpeg");
		UPLOAD_CONTENT_TYPES.add("image/bmp");
		UPLOAD_CONTENT_TYPES.add("image/png");
		UPLOAD_CONTENT_TYPES.add("image/gif");
	}

	@RequestMapping("modifyAvatar")
	public JsonResult doModifyAvatar(MultipartFile file, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());

		if(file.isEmpty()) {
			throw new RuntimeException("没有选择到上传文件");
		}
		if(file.getSize() > UPLOAD_MAX_SIZE) {
			throw new RuntimeException("文件大小不能超过" + UPLOAD_MAX_SIZE/1024 + "KB的文件");
		}
		if(!UPLOAD_CONTENT_TYPES.contains(file.getContentType())) {
			throw new RuntimeException("上传的文件不是指定的类型");
		}
		String parentPath = session.getServletContext().getRealPath("upload");
		File parent = new File(parentPath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		String originalFilename = file.getOriginalFilename();
		// 获取文件的扩展名
		int beginIndex = originalFilename.lastIndexOf(".");
		String suffix = originalFilename.substring(beginIndex);
		String filename = System.currentTimeMillis() + suffix;
		
//		String filepath = "./";
		File dest = new File(parent, filename);
//		System.err.print(dest.getAbsolutePath());
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"上传头像失败！文件无法被访问！");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"上传头像失败！读写数据时出现未知错误！");
		}
//		try {
//			file.transferTo(dest);
//			System.err.print(dest.getAbsolutePath());
//			userservice.modifyAvatar(uid, dest.getAbsolutePath());
//		}catch (IOException e){
//			return new JsonResult(e);
//		}
		String avatarPath = "/upload/" + filename;
		userservice.modifyAvatar(uid, avatarPath);

		return new JsonResult(avatarPath);
	}
}
