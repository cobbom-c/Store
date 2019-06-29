package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.District;

public interface DistrictMapper {
	List<District> findByParent(String Parent);
	
	District findByCode(String code);

}
