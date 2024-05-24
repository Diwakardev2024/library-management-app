package com.appsdevelopers.app.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;

@Service
public interface BookService {
	
	List<BookDto> getBooks();
	
	BookDto getBook(String bookId);
	
	BookDto createBook(BookDto book);

	BookDto getBookByBookId(String id);

	void deleteBook(String id);

}
