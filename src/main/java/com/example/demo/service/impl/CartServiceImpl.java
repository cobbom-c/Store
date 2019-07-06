package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.ICartService;
import com.example.demo.service.ex.ServiceException;

@Service
public class CartServiceImpl implements ICartService{
	@Autowired
	private CartMapper cartmapper;
	
	@Autowired
	private GoodsMapper goodsmapper;

	@Override
	public Integer addCart(Cart cart, String username) {
		Integer row = null;
		
		cart.setModifiedUser(username);
		cart.setModifiedTime(new Date());
		Cart car = cartmapper.findByUidGid(cart.getUid(), cart.getGid());
		if(car == null) {
			cart.setCreatedUser(username);
			cart.setCreateTime(new Date());
			row = cartmapper.addCart(cart);
		}
		else {
			cart.setNum(cart.getNum()+car.getNum());
			cart.setCid(car.getCid());
			row = cartmapper.freshNum(cart);
		}
		if(row != 1) {
			throw new ServiceException("加入购物车失败");
		}
		return row;
	}

	@Override
	public List<Goods> showCart(Integer uid) {
		List<Cart> cart = cartmapper.findByUid(uid);
		List<Goods> goodslist = new ArrayList<>();
		for(Cart car : cart) {
			Goods goods = goodsmapper.findById(car.getGid());
			goods.setNum(car.getNum());
			goodslist.add(goods);
		}
		return goodslist;
	}

	@Override
	public Integer updateNum(Cart cart) {
		Integer row = cartmapper.updateNum(cart);
		if(row != 1) {
			throw new ServiceException("商品数量更改失败");
		}
		return row;
	}

	@Override
	public Integer deleteCart(Integer uid, Long gid) {
		Integer row = cartmapper.deleteCart(uid, gid);
		if(row != 1) {
			throw new ServiceException("商品删除失败");
		}
		return row;
	}
	

}
