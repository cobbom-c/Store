package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	@Autowired
	DataSource datasource;
	
	@Autowired
	UserMapper usermapper;
	
//	@Test
	public void contextLoads() throws SQLException {
		Connection con = datasource.getConnection();
		System.err.print(con);
	}

//	@Test
	public void testAddUser() {
		User user = new User();
		user.setUsername("aaa");
		user.setPassword("12345");
		Integer row = usermapper.addUser(user);
		System.out.println("insert"+row+"line data");
		
	}
	
//	@Test
	public void testUpdatePassword() {
		Integer row = usermapper.updatePassword(27, "user1", "user", new Date());
		System.out.println("update"+row+"line data");
	}
	
	@Test
	public void testFindByUid() {
		User u = usermapper.findByUid(29);
		System.out.println(u);
	}
}
