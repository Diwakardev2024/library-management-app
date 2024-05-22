package com.appsdevelopers.app.ws;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.ListOfBooksEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;


@Repository
public interface ListOfBooksRepository extends JpaRepository<ListOfBooksEntity, Long> {
	
	List<ListOfBooksEntity> findAllByUserDetails(UserEntity userEntity);

}
