package com.appsdevelopers.app.ws;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;


@Repository
public interface ListOfBooksRepository extends JpaRepository<BookEntity, Long> {
	
	List<BookEntity> findAllByUserDetails(UserEntity userEntity);

}
