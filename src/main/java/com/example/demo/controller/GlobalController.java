package com.example.demo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.util.JsonResult;

@RestControllerAdvice
public class GlobalController {
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHanderRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}

}
