package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;

public interface ICartService {
	
	public Integer addCart(Cart cart, String username);
	
	public List<Goods> showCart(Integer uid);

	public Integer updateNum(Cart carts);
	
	public Integer deleteCart(Integer uid, Long gid);

}
