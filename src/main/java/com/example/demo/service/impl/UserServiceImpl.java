package com.example.demo.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.ServiceException;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper usermapper;
	
	private String getMD5Password(String password, String salt) {
		String str = password + salt;
		str = DigestUtils.md5Hex(str).toUpperCase();
		return str;
	}
	
	@Override
	public Integer reg(String username, String password, String password2) {
		if(StringUtils.isEmpty(username)) {
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new ServiceException("密码不能为空");
		}
		if(StringUtils.isEmpty(password2)) {
			throw new ServiceException("确认密码不能为空");
		}
		if(!password.equals(password2)) {
			throw new ServiceException("密码不一致");
		}
		User u = usermapper.findByUsername(username);
		if(u != null) {
			throw new ServiceException("用户名"+username+"已经被占用");
		}
		
		String salt = UUID.randomUUID().toString();
		String newpassword = getMD5Password(password, salt);
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(newpassword);
		user.setSalt(salt);
		user.setIsDelete(0);
		user.setCreatedUser(user.getUsername());
		user.setCreateTime(new Date());
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(new Date());
		
		Integer row = usermapper.addUser(user);

		if(row != 1) {
			throw new ServiceException("注册用户失败");
		}
		return row;
	}

	@Override
	public User log(String username, String password) {
		if(StringUtils.isEmpty(username)) {
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new ServiceException("密码不能为空");
		}
		
		User u = usermapper.findByUsername(username);
		if(u == null) {
			throw new ServiceException("用户名错误");
		}
		
		String salt = u.getSalt();
		String newpassword = getMD5Password(password, salt);
		
		if(!u.getPassword().equals(newpassword)) {
			throw new ServiceException("密码错误");
		}
		if(u.getIsDelete() == 1) {
			throw new ServiceException("该用户已被禁用");
		}
		
		u.setPassword(null);
		u.setSalt(null);
		u.setIsDelete(null);
		return u;
	}

	@Override
	public Integer modifyPassword(Integer uid, String oldpassword, String password, String password2) {
		if(StringUtils.isEmpty(uid)) {
			throw new ServiceException("用户没有登陆");
		}
		if(StringUtils.isEmpty(oldpassword)) {
			throw new ServiceException("原密码不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new ServiceException("新密码不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new ServiceException("确认密码不能为空");
		}
		if(!password.equals(password2)) {
			throw new ServiceException("密码不一致");
		}
		
		User u = usermapper.findByUid(uid);
		if(u == null) {
			throw new ServiceException("用户已经不存在");
		}
		if(u.getIsDelete() == 1) {
			throw new ServiceException("用户被禁用");
		}
		
		String salt = u.getSalt();
		String saltpassword = getMD5Password(oldpassword, salt);
		
		if(!u.getPassword().equals(saltpassword)) {
			throw new ServiceException("原密码不一致");
		}
		
		String newpassword = getMD5Password(password, salt);
		if(u.getPassword().equals(newpassword)) {
			throw new ServiceException("新密码与原密码不能相同");
		}
		u.setPassword(newpassword);
		
		Integer row = usermapper.updatePassword(uid, newpassword, u.getUsername(), new Date());
		
		return row;
	}

	@Override
	public User showUserData(Integer uid) {
		if(StringUtils.isEmpty(uid)) {
			throw new ServiceException("用户没有登陆");
		}
		
		User u = usermapper.findByUid(uid);
		if(u == null) {
			throw new ServiceException("用户已经不存在");
		}
		if(u.getIsDelete() == 1) {
			throw new ServiceException("用户被禁用");
		}

		u.setPassword(null);
		u.setSalt(null);
		u.setIsDelete(null);
		return u;
	}

	@Override
	public Integer modigyUserData(Integer uid, String phone, String email, Integer gender) {
		if(StringUtils.isEmpty(uid)) {
			throw new ServiceException("用户没有登陆");
		}
		
		User u = usermapper.findByUid(uid);
		if(u == null) {
			throw new ServiceException("用户已经不存在");
		}
		if(u.getIsDelete() == 1) {
			throw new ServiceException("用户被禁用");
		}
		
		u.setPhone(phone);
		u.setEmail(email);
		u.setGender(gender);
		
		Integer row = usermapper.updateUserData(uid, phone, email, gender);
		if(row != 1) {
			throw new ServiceException("用户信息修改失败");
		}
		return row;
	}

}
