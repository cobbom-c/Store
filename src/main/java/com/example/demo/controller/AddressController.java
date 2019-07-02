package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.service.IAddressService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("address")
public class AddressController {
	
	@Autowired
	private IAddressService addressservice;
	
	@RequestMapping("add")
	public JsonResult doAddAddress(Address address, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		address.setUid(uid);

		if(address.getAid() == null)
			addressservice.addAddress(address, username);
		else 
			addressservice.modifyAddress(address, username);
		return new JsonResult("添加地址OK");
	}
	
	@RequestMapping("show")
	public JsonResult doShowAddress(HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		
		List<Address> listaddr = new ArrayList<>();
		listaddr = addressservice.showAddress(uid);
		
		return new JsonResult(listaddr);
	}
	
	@RequestMapping("del")
	public JsonResult doDelAddress(Integer aid) {
		addressservice.delAddress(aid);
		return new JsonResult("删除地址OK");
		
	}
	
	@RequestMapping("default")
	public JsonResult doSetDefault(Integer defaultAid, Integer aid, HttpSession session) {
		String username = session.getAttribute("username").toString();

		addressservice.setDefault(defaultAid, aid, username);
		return new JsonResult("默认设置OK");
	}

	@RequestMapping("modify")
	public JsonResult doSetDefault(Integer aid) {
		Address address = addressservice.getAddress(aid);
		return new JsonResult(address);
	}
}
