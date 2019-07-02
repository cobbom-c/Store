package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Address;

@Mapper
public interface AddressMapper {
	Integer addAddress(Address address);

	Integer countByUid(Integer uid);
	
	List<Address> listByUid(Integer uid);

	Integer delByAid(Integer aid);
	
	Integer setDefault(Integer aid, String username, Date time);
	
	Integer unsetDefault(Integer aid, String username, Date time);
	
	Address getByAid(Integer aid);
	
	Integer modifyAddress(Address address);
}
