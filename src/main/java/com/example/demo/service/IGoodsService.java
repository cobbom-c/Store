package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Goods;

public interface IGoodsService {
	public List<Goods> showGoods();
	
	public Goods showDeitail(Long id);

}
