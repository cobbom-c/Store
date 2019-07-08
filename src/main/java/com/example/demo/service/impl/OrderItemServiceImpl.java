package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.service.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService{
	
	@Autowired
	private GoodsMapper goodsmapper;
	
	@Autowired
	private CartMapper cartmapper;
	
	@Autowired
	private OrderItemMapper orderitemmapper;

	@Override
	public Integer addOrderItem(Long gid, Integer oid, Integer uid, String username) {
		Integer row = null;
		OrderItem orderitem = new OrderItem();
		Goods goods = goodsmapper.findById(gid);
		Cart cart = cartmapper.findByUidGid(uid, gid);
		orderitem.setOid(oid);
		orderitem.setGid(gid);
		orderitem.setTitle(goods.getTitle());
		orderitem.setImage(goods.getImage());
		orderitem.setPrice(goods.getPrice());
		orderitem.setNum(cart.getNum());
		orderitem.setCreatedUser(username);
		orderitem.setCreateTime(new Date());
		orderitem.setModifiedTime(new Date());
		orderitem.setModifiedUser(username);
		
		row = orderitemmapper.addOrderItem(orderitem);
		return row;
	}

	@Override
	public List<OrderItem> listByOid(Integer oid) {
		List<OrderItem> orderitem = orderitemmapper.findByOid(oid);
		return orderitem;
	}

}
