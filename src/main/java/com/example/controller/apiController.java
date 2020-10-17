package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entiry.UserEntity;
import com.example.service.apiService;

@RestController("api")
public class apiController {
	
	@Autowired
	apiService service;
	
	@GetMapping("hello")
	public String hello() {
		return "Hello";
	}
	
	@PostMapping("save")
	public UserEntity save(@RequestBody UserEntity entity) {
		return service.save(entity);
	}
	
	@GetMapping("findAll")
	public List<UserEntity> findAll() {
		return service.findAll();
	}
	
	@GetMapping("findById")
	public UserEntity findById(String id) {
		return service.findById(id);
	}
	
	@GetMapping("findByName")
	public UserEntity findByName(String name) {
		return service.findByName(name);
	}
	
	@GetMapping("count")
	public Long count() {
		return service.totalRecordCount();
	}
	
//	@GetMapping("search")
//	public Long search() {
//		return service.totalRecordCount();
//	}
}
