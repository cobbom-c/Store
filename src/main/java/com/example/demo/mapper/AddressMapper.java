package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Address;

@Mapper
public interface AddressMapper {
	Integer addAddress(Address address);

	Integer countByUid(Integer uid);

}
