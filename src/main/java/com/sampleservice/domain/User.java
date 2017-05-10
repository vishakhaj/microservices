package com.sampleservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;
	private String name;
	private String emailAddress;
	private String nickname;

	public User(){
		
	}
	
	public User(String id, String name, String emailAddress, String nickname) {
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getNickname() {
		return nickname;
	}

}
