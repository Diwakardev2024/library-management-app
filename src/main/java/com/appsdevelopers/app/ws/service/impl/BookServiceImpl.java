package com.appsdevelopers.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.entity.BookEntity;
import com.appsdevelopers.app.ws.entity.BookShelfEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;
import com.appsdevelopers.app.ws.exceptions.BookServiceException;
import com.appsdevelopers.app.ws.exceptions.UserServiceException;
import com.appsdevelopers.app.ws.repositories.BookShelfRepository;
import com.appsdevelopers.app.ws.repositories.BooksRepository;
import com.appsdevelopers.app.ws.repositories.UserRepository;
import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.shared.Utils;
import com.appsdevelopers.app.ws.ui.model.response.ErrorMessages;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.IssueBookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.ReturnBookDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

@Service
@Scope("singleton")
@Qualifier(value = "offline-book")
public class BookServiceImpl implements BookService {

	@Autowired
	BooksRepository bookRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper mapper;

	@Autowired
	BookShelfRepository bookShelfRepository;

	@Autowired
	UserRepository userRepository;

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
	public BookDto addBook(BookDto book) {

		if (bookRepository.findByBookId(book.getBookId()) != null)
			throw new RuntimeException(" Records already exists ");

		BookEntity bookEntity = new BookEntity();
		mapper.map(book, bookEntity);
		String publicUserId = utils.generatedBookId(30);
		bookEntity.setBookId(publicUserId);
		BookEntity storedUserDetails = bookRepository.save(bookEntity);

		for (long i = 0; i < bookEntity.getCount(); i++) {

			BookShelfEntity bookShelfEntity = new BookShelfEntity();
			bookShelfEntity.setSerialNo(utils.generatedSerialNumber(20));
			bookShelfEntity.setBookDetails(storedUserDetails);
			bookShelfEntity.setAvailable(true);
			bookShelfEntity = bookShelfRepository.save(bookShelfEntity);
		}

		BookDto returnValue = mapper.map(storedUserDetails, BookDto.class);

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
			throw new BookServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		bookRepository.delete(bookEntity);

	}

	@Override
	public BookDto updateBookCount(String id, long value) {

		// 1. find the book by its Id
		BookEntity bookEntity = bookRepository.findByBookId(id);
		// 2. check book id is avaiable or not
		if (bookEntity == null)
			throw new BookServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		// save the updated book object
		// creating BookShelf Entry
		for (long i = 0; i < value; i++) {

			BookShelfEntity bookShelfEntity = new BookShelfEntity();
			bookShelfEntity.setSerialNo(utils.generatedSerialNumber(20));
			bookShelfEntity.setAvailable(true);
			bookShelfEntity = bookShelfRepository.save(bookShelfEntity);
		}
		// update the count value of the book
		bookEntity.setCount(bookEntity.getCount() + value);
		bookEntity = bookRepository.save(bookEntity);

		// create BookDto and update it with BookEntity data
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(bookEntity, returnValue);
		return returnValue;
	}

	@Override
	public IssueBookDto issueBooks(String userId, List<String> bookId) {

		// Validate if user exist in the database or not
		UserEntity user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new UserServiceException("UserId is not valid");
		}

		// validate if books are exist in the database
		List<String> userRequestBookIds = bookId.stream().distinct().collect(Collectors.toList());

//		List<BookEntity> books = bookRepository.findAllByBookIdIn(userRequestBookIds);

		List<BookShelfEntity> bookShelfEntities = bookShelfRepository.findByBookId(bookId);

		List<BookShelfEntity> booksToBeIssued = new ArrayList<>();

		for (String item : userRequestBookIds) {
			BookShelfEntity bookShelfEntity = bookShelfEntities.stream()
					.filter(b -> b.getBookDetails().getBookId().equals(item) && b.isAvailable()).findFirst().get();
			bookShelfEntity.setAvailable(false);
			bookShelfEntity.setUserDetails(user);
			booksToBeIssued.add(bookShelfEntity);
		}

		bookShelfRepository.saveAll(booksToBeIssued);

//		user.setBookShelfEntities(booksToBeIssued);

//		IssueBookDto returnValue = new IssueBookDto();

		UserDto userDto = mapper.map(user, UserDto.class);

		IssueBookDto issueBookDto = new IssueBookDto();
		issueBookDto.setUser(userDto);
		issueBookDto.setBookShelfEntities(booksToBeIssued);

		return issueBookDto;

		// check books are available in the database

		// each user will take multiplebooks with different book ids.

	}

	@Override
	public ReturnBookDto returnBooks(String userId, List<String> serialNo) {

		UserEntity user = userRepository.findByUserId(userId);
		
		List<String> userRequestSerialNo = serialNo.stream().distinct().collect(Collectors.toList());
		
		List<BookShelfEntity> bookShelfEntities	=bookShelfRepository.findBySerialNo(serialNo);
	
		List<BookShelfEntity> booksToBeReturned = new ArrayList<>();
		
		for(String item:userRequestSerialNo) {
			
			BookShelfEntity bookShelfEntity = bookShelfEntities.stream()
					.filter(b -> b.getSerialNo().equals(item)).findFirst().get();
			if(!bookShelfEntity.isAvailable() ) {
				
			bookShelfEntity.setAvailable(true);
			bookShelfEntity.setUserDetails(user);
		
			booksToBeReturned.add(bookShelfEntity);
			}
			
			else
				throw new RuntimeException("User have been already sumbmitted that book Serial Number : " +item); 
		}
		
		 bookShelfRepository.saveAll(booksToBeReturned);
		
		UserDto userDto = mapper.map(user, UserDto.class);
		ReturnBookDto returnBookDto=new ReturnBookDto();
		returnBookDto.setUserId(userDto);
		returnBookDto.setSerialNo(booksToBeReturned);

		
		return returnBookDto;
	}

}
