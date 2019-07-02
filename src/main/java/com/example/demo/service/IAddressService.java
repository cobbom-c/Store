package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Address;

public interface IAddressService {
	public Integer addAddress(Address address, String username);
	
	public List<Address> showAddress(Integer uid);

	public Integer delAddress(Integer aid);
	
	public Integer setDefault(Integer defaultAid, Integer aid, String username);
	
	public Address getAddress(Integer aid);
	
	public Integer modifyAddress(Address address, String username);
}
