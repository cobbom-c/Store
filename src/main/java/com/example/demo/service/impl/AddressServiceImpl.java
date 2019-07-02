package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	@Override
	public List<Address> showAddress(Integer uid) {
		if(StringUtils.isEmpty(uid)) {
			 throw new ServiceException("显示地址列表没有给uid");
		}

		List<Address> listaddr = new ArrayList<>();
		listaddr = addressmapper.listByUid(uid);
		
		for(Address addr : listaddr) {
			String phone = addr.getPhone();
			String phonefix = phone.substring(0, 4);
			String phonesufix = phone.substring(phone.length() - 4, phone.length());
			addr.setPhone(phonefix + "***" + phonesufix);
		}
		
		return listaddr;
	}

	@Override
	public Integer delAddress(Integer aid) {
		if(StringUtils.isEmpty(aid)) {
			 throw new ServiceException("显示地址列表没有给aid");
		}
		
		int row = addressmapper.delByAid(aid);
		if(row != 1) {
			throw new ServiceException("删除地址失败");
		}
		return row;
	}

	@Override
	public Integer setDefault(Integer defaultAid, Integer aid, String username) {
		if(StringUtils.isEmpty(defaultAid)) {
			 throw new ServiceException("显示地址列表没有给默认aid");
		}
		
		if(StringUtils.isEmpty(aid)) {
			 throw new ServiceException("显示地址列表没有给aid");
		}
		
		int row = addressmapper.setDefault(aid, username, new Date());
		
		row += addressmapper.unsetDefault(defaultAid, username, new Date());
		if(row != 2) {
			throw new ServiceException("默认设置失败");
		}
		return row;
	}

	@Override
	public Address getAddress(Integer aid) {
		if(StringUtils.isEmpty(aid)) {
			 throw new ServiceException("显示地址列表没有给aid");
		}
		
		Address addr = addressmapper.getByAid(aid);
		return addr;
	}

	@Override
	public Integer modifyAddress(Address address, String username) {
		String district = getDistrict(address.getProvince(), address.getCity(), address.getArea());

		Address addr = addressmapper.getByAid(address.getAid());

		if(addr.getProvince()==null||addr.getCity()==null||addr.getArea()==null) {
			address.setProvince(addr.getProvince());
			address.setCity(addr.getCity());
			address.setArea(addr.getArea());
		}
		
		address.setDistrict(district);
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		
		int row = addressmapper.modifyAddress(address);
		if(row != 1) {
			throw new ServiceException("修改地址失败");
		}
		
		return row;
	}

}
