package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.OrderItem;

public interface IOrderItemService {
	public Integer addOrderItem(Long gid, Integer oid, Integer uid, String username);
	
	public List<OrderItem> listByOid(Integer oid);

}
