package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("servlet")
public class LogController {
	
	private static Logger log = Logger.getLogger(LogController.class);
	
	@RequestMapping("logServlet")
	public void logServlet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String qs = URLDecoder.decode(request.getQueryString(), "utf-8");
		
		//请求串中各指标以&符号分割
		String []  attrs = qs.split("\\&");
		StringBuffer buf = new StringBuffer();
		for(String attr : attrs){
			//每个指标以kv形式存在，中间用=分割
			String [] kv = attr.split("=");
			String key = kv[0];		//指标名称
			String val = kv.length == 2 ? kv[1] : "";		//指标值
			buf.append(val + "|");							//指标以|分割
		}
		buf.append(request.getRemoteAddr());				//增加服务器端IP地址指标
		
		
		String loginfo = buf.toString();
		log.info(loginfo);
	}

}
