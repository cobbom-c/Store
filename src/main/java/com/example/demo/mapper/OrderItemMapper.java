package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.OrderItem;

@Mapper
public interface OrderItemMapper {
	String TABLE_NAME = " t_order_item ";
	String UPDATE_FIELD = "oid, gid, title, image, price, num, created_user, created_time, modified_user, modified_time";
	String INSERT_SQL = "INSERT INTO" + TABLE_NAME + "(" + UPDATE_FIELD + ") VALUES(#{oid}, #{gid}, #{title}, #{image}, "
			+ "#{price}, #{num}, "
			+ "#{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})";
	String SELECT_SQL = "SELECT * FROM" + TABLE_NAME + "WHERE oid = #{oid}";

	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Integer addOrderItem(OrderItem orderitem);
	
	@Select(SELECT_SQL)
	List<OrderItem> findByOid(Integer oid);
}
