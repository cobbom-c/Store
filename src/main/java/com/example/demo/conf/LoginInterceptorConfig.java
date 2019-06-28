package com.example.demo.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = new ArrayList<>();
		patterns.add("/bootstrap3/**");
		patterns.add("/css/**");
		patterns.add("/images/**");
		patterns.add("/js/**");
		patterns.add("/user/reg");
		patterns.add("/user/log");
		patterns.add("/web/register.html");
		patterns.add("/web/login.html");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
	}

}
