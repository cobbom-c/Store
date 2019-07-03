package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.Goods;

public interface GoodsMapper {
	List<Goods> findHotList();
	
	Goods findById(Long id);

}
