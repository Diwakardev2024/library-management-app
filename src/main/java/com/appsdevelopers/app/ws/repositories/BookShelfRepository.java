package com.appsdevelopers.app.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appsdevelopers.app.ws.entity.BookShelfEntity;


@Repository
public interface BookShelfRepository extends JpaRepository<BookShelfEntity, String> {

	@Query(nativeQuery = true, value = "select * from book_shelf where book_id IN :bookIds")
	List<BookShelfEntity> findByBookId(List<String> bookIds);
	
	@Query(nativeQuery=true,value="select * from book_shelf where serial_no IN :serialNo")
	List<BookShelfEntity> findBySerialNo(List<String> serialNo);
	
}
