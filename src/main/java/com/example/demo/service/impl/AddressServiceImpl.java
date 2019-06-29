package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.District;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.service.IAddressService;
import com.example.demo.service.IDistrictService;
import com.example.demo.service.ex.ServiceException;

@Service
public class AddressServiceImpl implements IAddressService{
	
	private static final int MAX_ADDRESS_COUNT = 20;
	
	@Autowired
	private AddressMapper addressmapper;
	
	@Autowired
	private IDistrictService districtservice;
	
	private String getDistrict(String provinceCode,String cityCode,String areaCode) {
		District province = districtservice.getByCode(provinceCode);
		District city = districtservice.getByCode(cityCode);
		District area = districtservice.getByCode(areaCode);
		StringBuffer district = new StringBuffer();
		if(province!= null) {
			district.append(province.getName());
		}
		if(city !=null) {
			district.append(city.getName());
		}
		if(area != null) {
			district.append(area.getName());
		}
		return district.toString();
	}

	@Override
	public Integer addAddress(Address address, String username) {
		Integer uid = address.getUid();
		
		Integer count = addressmapper.countByUid(uid);
		
		if(count > MAX_ADDRESS_COUNT) {
			throw new ServiceException("添加地址失败，上限不能超过" + MAX_ADDRESS_COUNT + "个");
		}
		
		Integer isDefault = (count == 0?1:0);
		
		address.setIsDefault(isDefault);
		
		String district = getDistrict(address.getProvince(), address.getCity(), address.getArea());
		
		address.setDistrict(district);
		address.setCreatedUser(username);
		address.setCreateTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		
		Integer row = addressmapper.addAddress(address);
		if(row != 1) {
			throw new ServiceException("创建地址失败");
		}
		return row;
	}

}
