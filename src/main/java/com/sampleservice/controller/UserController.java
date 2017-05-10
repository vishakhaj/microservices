package com.sampleservice.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampleservice.repository.UserRepositoryImpl;

@RestController
public class UserController {

	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@RequestMapping("/sample")
	public void getUsers() throws URISyntaxException{
		userRepositoryImpl.fetchConsulValueUrl();
	}
}
