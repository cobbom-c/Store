package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.District;

public interface IDistrictService {
	List<District> getByParent(String parent);
	
	District getByCode(String code);

}
