package com.appsdevelopers.app.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public List<BookDto> getBooks(String userId) {
		
		return null;
	}

	@Override
	public BookDto getBook(String addressId) {
		
		return null;
	}

}
