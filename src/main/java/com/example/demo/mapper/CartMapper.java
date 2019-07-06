package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Cart;

@Mapper
public interface CartMapper {

	String TABLE_NAME = " t_cart ";
	String UPDATE_FIELD = "uid, gid, num, created_user, created_time, modified_user, modified_time";
	String INSERT_SQL = "INSERT INTO" + TABLE_NAME + "(" + UPDATE_FIELD + ") VALUES(#{uid}, #{gid}, #{num}, "
			+ "#{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})"; 
	String SELECT_SQL = "SELECT uid, gid, num FROM" + TABLE_NAME + "WHERE uid = #{uid}";
	String UPDATE_SQL = "UPDATE" + TABLE_NAME + "SET num = #{num}, "
			+ "modified_user = #{modifiedUser}, "
			+ "modified_time = #{modifiedTime} WHERE uid = #{uid} AND gid = #{gid}";
	String DELETE_SQL = "DELETE FROM" + TABLE_NAME + "WHERE uid = #{uid} AND gid = #{gid}";
	String FIND_SQL = "SELECT * FROM" + TABLE_NAME + "WHERE uid = #{uid} AND gid = #{gid}";
	String FRESH_SQL = "UPDATE" + TABLE_NAME + "SET num = #{num}, "
			+ "modified_user = #{modifiedUser}, "
			+ "modified_time = #{modifiedTime} "
			+ "WHERE cid = #{cid}";
	
	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys = true, keyProperty = "cid", keyColumn = "cid")
	Integer addCart(Cart cart);

	@Select(SELECT_SQL)
	List<Cart> findByUid(Integer uid);
	
	@Update(UPDATE_SQL)
	Integer updateNum(Cart cart);
	
	@Delete(DELETE_SQL)
	Integer deleteCart(Integer uid, Long gid);
	
	@Select(FIND_SQL)
	Cart findByUidGid(Integer uid, Long gid);
	
	@Update(FRESH_SQL)
	Integer freshNum(Cart cart);
}
