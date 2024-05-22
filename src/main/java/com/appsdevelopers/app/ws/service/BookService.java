package com.appsdevelopers.app.ws.service;

import java.util.List;

import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;


public interface BookService {
	
	List<BookDto> getBooks(String userId);
	
	BookDto getBook(String addressId);

}
