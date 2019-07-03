package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.ICartService;
import com.example.demo.service.ex.ServiceException;

@Service
public class CartServiceImpl implements ICartService{
	@Autowired
	private CartMapper cartmapper;

	@Override
	public Integer addCart(Cart cart, String username) {
		cart.setCreatedUser(username);
		cart.setCreateTime(new Date());
		cart.setModifiedUser(username);
		cart.setModifiedTime(new Date());
		Integer row = cartmapper.addCart(cart);
		
		if(row != 1) {
			throw new ServiceException("加入购物车失败");
		}
		return row;
	}
	

}
