package com.example.demo;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@Configuration
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	public MultipartConfigElement createMultipartConfig() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		DataSize maxFileSize = DataSize.ofMegabytes(50);
		DataSize maxRequestSize = DataSize.ofMegabytes(50);
		factory.setMaxFileSize(maxFileSize);
		factory.setMaxRequestSize(maxRequestSize);
		return factory.createMultipartConfig();
	}

}
