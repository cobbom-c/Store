package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.service.ICartService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("cart")
public class CartController {
	@Autowired
	private ICartService cartservice;
	
	@RequestMapping("add")
	public JsonResult addToCart(Long id, Integer num, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();

		Cart cart = new Cart();
		cart.setGid(id);
		cart.setNum(num);
		cart.setUid(uid);
		
		cartservice.addCart(cart, username);
		
		return new JsonResult("加入购物车成功");
	}

}
