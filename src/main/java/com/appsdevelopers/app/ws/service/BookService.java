package com.appsdevelopers.app.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;

@Service
public interface BookService {
	
	List<BookDto> getBooks();
	
	BookDto getBook(String bookId);
	
	BookDto addBook(BookDto book);

	BookDto getBookByBookId(String id);

	void deleteBook(String id);

	BookDto updateBookCount(String id, long value);
	
	 void issueBooks(String userId,List<String> bookId);
}
