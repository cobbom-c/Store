package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Goods;
import com.example.demo.service.impl.GoodsServiceImpl;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private GoodsServiceImpl goodsservice;
	
	@RequestMapping("hot")
	public JsonResult doShowGoods() {
		List<Goods> goods = goodsservice.showGoods();
		return new JsonResult(goods);
	}

	@RequestMapping("detail")
	public JsonResult doShowDetail(Long id) {
		Goods goods = goodsservice.showDeitail(id);
		return new JsonResult(goods);
	}
}
