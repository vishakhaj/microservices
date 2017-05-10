package com.sampleservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sampleservice.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
