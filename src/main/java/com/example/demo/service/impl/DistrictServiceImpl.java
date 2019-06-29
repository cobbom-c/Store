package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.District;
import com.example.demo.mapper.DistrictMapper;
import com.example.demo.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{
	@Autowired
	private DistrictMapper districtmapper;

	@Override
	public List<District> getByParent(String parent) {
		return findByParent(parent);
	}
	private List<District> findByParent(String parent){
		return districtmapper.findByParent(parent);
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}
	private District findByCode(String code) {
		return districtmapper.findByCode(code);
	}
	

}
