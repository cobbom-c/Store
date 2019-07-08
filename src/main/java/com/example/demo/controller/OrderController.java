package com.example.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private IOrderService orderservice;
	
	@RequestMapping("add")
	public JsonResult doAddOrder(Long totalprice, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		
		Order order = new Order();
		order.setUid(uid);
		order.setCreatedUser(username);
		order.setCreateTime(new Date());
		order.setModifiedTime(new Date());
		order.setModifiedUser(username);
		order.setTotalPrice(totalprice);
		
		Integer oid = orderservice.addOrder(order);
		
		return new JsonResult(oid);
	}
	
	@RequestMapping("show")
	public JsonResult doShowOrder(Integer oid) {
		
		return new JsonResult(orderservice.findByOid(oid));
	}
	
	@RequestMapping("pay")
	public JsonResult doPay(Integer oid, Integer aid, HttpSession session) {
		String username = session.getAttribute("username").toString();

		orderservice.updateAddress(oid, aid, username);
		return new JsonResult("生成订单OK");
	}

}
