package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.demo.entity.Cart;

@Mapper
public interface CartMapper {

	String TABLE_NAME = " t_cart";
	String UPDATE_FIELD = "uid, gid, num, created_user, created_time, modified_user, modified_time";
	String INSERT_SQL = "INSERT INTO" + TABLE_NAME + "(" + UPDATE_FIELD + ") VALUES(#{uid}, #{gid}, #{num}, "
			+ "#{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})"; 
	
	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys = true, keyProperty = "cid", keyColumn = "cid")
	Integer addCart(Cart cart);

}
