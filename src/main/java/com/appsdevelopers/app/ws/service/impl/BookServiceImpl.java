package com.appsdevelopers.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.BooksRepository;
import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.exceptions.BookServiceException;
import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.shared.Utils;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BooksRepository bookRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<BookDto> getBooks() {

		List<BookDto> returnValue = new ArrayList<>();
		List<BookEntity> bookEntities = bookRepository.findAll();

		if (bookEntities == null)
			throw new RuntimeException("Users not found");
		for (BookEntity entity : bookEntities) {

			BookDto bookDto = new BookDto();

			BeanUtils.copyProperties(entity, bookDto);

			returnValue.add(bookDto);

		}
		return returnValue;

	}

	@Override
	public BookDto getBook(String bookId) {

		return null;
	}

	@Override
	public BookDto createBook(BookDto book) {

		if (bookRepository.findByBookId(book.getBookId()) != null)
			throw new RuntimeException(" Records already exists ");

		BookEntity bookEntity = new BookEntity();
		mapper.map(book, bookEntity);

		String publicUserId = utils.generatedBookId(30);
		bookEntity.setBookId(publicUserId);
		BookEntity storedUserDetails = bookRepository.save(bookEntity);
		BookDto returnValue = new BookDto();
		returnValue = mapper.map(storedUserDetails, BookDto.class);

		return returnValue;
	}

	@Override
	public BookDto getBookByBookId(String id) {
		
		BookDto returnValue = new BookDto();
		BookEntity bookEntity = bookRepository.findByBookId(id);
		if (bookEntity == null)
			throw new RuntimeException("BookId not found");
		BeanUtils.copyProperties(bookEntity, returnValue);
		return returnValue;

	}

	@Override
	public void deleteBook(String id) {
		BookEntity bookEntity = bookRepository.findByBookId(id);
		if (bookEntity == null)
			throw new BookServiceException(com.appsdevelopers.app.ws.ui.model.response.ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		bookRepository.delete(bookEntity);
		
	}

}
