package com.appsdevelopers.app.ws;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.entity.BookShelfEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;


@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long> {
	
	List<BookEntity> findAllByUserDetails(UserEntity userEntity);
	
	BookEntity findByBookId(String bookId);

	List<BookEntity> findAllByBookIdIn(List<String> userRequestBookIds);


//	boolean findByTitle(String bookId);
	

}
