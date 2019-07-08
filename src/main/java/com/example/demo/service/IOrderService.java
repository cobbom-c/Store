package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Address;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

public interface IOrderService {
	public Integer addOrder(Order order);
	
	public List<Order> listByUid(Integer uid);

	public Integer updateAddress(Integer oid, Integer aid, String username);
	
	public List<OrderItem> findByOid(Integer oid);
}
