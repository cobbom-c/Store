package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IOrderItemService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("orderitem")
public class OrderItemController {
	
	@Autowired
	private IOrderItemService orderitemservice;
	
	@RequestMapping("add")
	public JsonResult doAddOrderitem(Long gid, Integer oid, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();

		
		orderitemservice.addOrderItem(gid, oid, uid, username);
		
		return new JsonResult("添加订单项成功");
	}

}
