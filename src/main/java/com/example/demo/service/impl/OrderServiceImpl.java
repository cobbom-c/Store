package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private AddressMapper addressmapper;
	
	@Autowired
	private OrderMapper ordermapper;
	
	@Autowired
	private OrderItemMapper orderitemmapper;

	@Override
	public Integer addOrder(Order order) {
		order.setState(0);
		order.setOrderTime(new Date());
		
		ordermapper.addOrder(order);
		return order.getOid();
	}

	@Override
	public List<Order> listByUid(Integer uid) {
		List<Order> order = ordermapper.findByUid(uid);
		return order;
	}

	@Override
	public Integer updateAddress(Integer oid, Integer aid, String username) {
		Order order = new Order();
		Address address = addressmapper.getByAid(aid);
		order.setOid(oid);
		order.setAddress(address.getDistrict() + address.getAddress());
		order.setName(address.getName());
		order.setPhone(address.getPhone());
		order.setState(1);
		order.setModifiedTime(new Date());
		order.setModifiedUser(username);
		order.setPayTime(new Date());
		Integer row = ordermapper.updateAddress(order);
		return row;
	}

	@Override
	public List<OrderItem> findByOid(Integer oid) {
		List<OrderItem> orderitem = orderitemmapper.findByOid(oid);
		
		return orderitem;
	}

}
