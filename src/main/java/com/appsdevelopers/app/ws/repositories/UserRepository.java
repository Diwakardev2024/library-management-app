package com.appsdevelopers.app.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);

}
