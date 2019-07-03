package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService{
	
	@Autowired
	private GoodsMapper goodsmapper;

	@Override
	public List<Goods> showGoods() {
		return findHotList();
	}
	private List<Goods> findHotList(){
		return goodsmapper.findHotList();
	}
	
	@Override
	public Goods showDeitail(Long id) {
		return findById(id);
	}
	private Goods findById(Long id){
		return goodsmapper.findById(id);
	}


}
