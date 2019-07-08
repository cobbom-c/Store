package com.example.demo.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Order;

@Mapper
public interface OrderMapper {
	String TABLE_NAME = " t_order ";
	String UPDATE_FIELD = "uid, name, phone, address, total_price, state, order_time, pay_time, created_user, created_time, modified_user, modified_time";
	String INSERT_SQL = "INSERT INTO" + TABLE_NAME + "(" + UPDATE_FIELD + ") VALUES(#{uid}, #{name}, #{phone}, "
			+ "#{address}, #{totalPrice}, #{state}, #{orderTime}, #{payTime}, "
			+ "#{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})";
	String UPDATE_SQL = "UPDATE" + TABLE_NAME + "SET name = #{name}, phone = #{phone}, address = #{address}, "
			+ "state = #{state}, modified_user = #{modifiedUser}, "
			+ "modified_time = #{modifiedTime} WHERE oid = #{oid}";
	String SELECT_SQL = "SELECT oid FROM" + TABLE_NAME + "WHERE uid = #{uid}";
	
	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys = true, keyProperty = "oid", keyColumn = "oid")
	Integer addOrder(Order order);
	
	@Update(UPDATE_SQL)
	Integer updateAddress(Order order);
	
	@Select(SELECT_SQL)
	List<Order> findByUid(Integer uid);

}
