package com.appsdevelopers.app.ws.service;

import java.util.List;

import com.appsdevelopers.app.ws.ui.model.shared.dto.ListOfBooksDto;


public interface ListOfBooksService {
	
	List<ListOfBooksDto> getListOfBooks(String userId);
	
	ListOfBooksDto getListOfBook(String addressId);

}
