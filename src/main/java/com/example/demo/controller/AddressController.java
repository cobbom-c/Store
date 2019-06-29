package com.example.demo.controller;

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

		addressservice.addAddress(address, username);
		return new JsonResult("添加地址OK");
	}

}
