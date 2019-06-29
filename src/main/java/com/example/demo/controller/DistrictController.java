package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.District;
import com.example.demo.service.IDistrictService;
import com.example.demo.util.JsonResult;

@RestController
@RequestMapping("district")
public class DistrictController {
	@Autowired
	private IDistrictService districtservice;
	
	@RequestMapping("/")
	public JsonResult doAddAddress(String parent) {
		List<District> list = districtservice.getByParent(parent);
		return new JsonResult(list);
	}

}
