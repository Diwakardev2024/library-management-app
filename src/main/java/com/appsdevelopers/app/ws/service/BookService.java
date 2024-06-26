package com.appsdevelopers.app.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.IssueBookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.ReturnBookDto;

@Service
public interface BookService {
	
	List<BookDto> getBooks();
	
	BookDto getBook(String bookId);
	
	BookDto addBook(BookDto book);

	BookDto getBookByBookId(String id);

	void deleteBook(String id);

	BookDto updateBookCount(String id, long value);
	
	 IssueBookDto issueBooks(String userId,List<String> bookId);
	 
	 ReturnBookDto returnBooks(String userId,List<String> serialNo);
}
