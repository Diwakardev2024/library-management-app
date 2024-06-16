package com.appsdevelopers.app.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.IssueBookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.ReturnBookDto;

@Service
//@Primary
@Qualifier(value = "online-book")
public class OnlineBookServiceImpl implements BookService {

	@Override
	public List<BookDto> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto getBook(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto addBook(BookDto book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto getBookByBookId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BookDto updateBookCount(String id, long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IssueBookDto issueBooks(String userId, List<String> bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBookDto returnBooks(String userId, List<String> serialNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
