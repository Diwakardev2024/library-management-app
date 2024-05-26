package com.appsdevelopers.app.ws;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.entity.BookShelfEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;


@Repository
public interface BookShelfRepository extends JpaRepository<BookShelfEntity, String> {
	
	List<BookShelfEntity> findAllByBookIdIn(List<String> userRequestBookIds);
	
//	List<BookShelfEntity> findAllByUserDetails(UserEntity userEntity);	
	
//	BookEntity findByBookId(String bookId);


//	boolean findByTitle(String bookId);
	

}
