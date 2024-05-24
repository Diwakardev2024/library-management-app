package com.appsdevelopers.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.ui.model.request.LibraryBookDetailsRequestModel;
import com.appsdevelopers.app.ws.ui.model.response.ErrorMessages;
import com.appsdevelopers.app.ws.ui.model.response.LibraryBookResponse;
import com.appsdevelopers.app.ws.ui.model.response.OperationalStatusModel;
import com.appsdevelopers.app.ws.ui.model.shared.dto.BookDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/libraryBook")
public class LibraryBookController {
	
	private static final Logger logger=LogManager.getLogger(LibraryBookController.class);
	@Autowired
	BookService bookService;

	
	@Autowired
	ModelMapper mapper;
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public LibraryBookResponse createBook(@Valid @RequestBody LibraryBookDetailsRequestModel bookDetails)
			throws Exception {

		LibraryBookResponse returnValue = new LibraryBookResponse();
		
		if (bookDetails.getTitle().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		BookDto bookDto=new BookDto();
		BeanUtils.copyProperties(bookDetails,bookDto );
//		BookDto bookDto = mapper.map(bookDetails, BookDto.class);
		BookDto createdBook = bookService.createBook(bookDto);
		returnValue = mapper.map(createdBook, LibraryBookResponse.class);

		return returnValue;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE  })
	public List<LibraryBookResponse> getBooks() {

		List<LibraryBookResponse> returnValue = new ArrayList<>();
		List<BookDto> bookDtos = bookService.getBooks();
		for (BookDto bookdto : bookDtos) {

			LibraryBookResponse bookResponse = new LibraryBookResponse();
			BeanUtils.copyProperties(bookdto, bookResponse);
			returnValue.add(bookResponse);

		}
		return returnValue;
	
}
	
	@GetMapping(path="/{id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryBookResponse getBook(@PathVariable String id) {
		
		logger.info("Start Get user");
		
		LibraryBookResponse returnValue=new LibraryBookResponse();
		
		BookDto bookdto=bookService.getBookByBookId(id);
		returnValue = mapper.map(bookdto, LibraryBookResponse.class);
		logger.info("Request successfull");
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public OperationalStatusModel deleteBook(@PathVariable String id) {
		
		OperationalStatusModel returnValue=new OperationalStatusModel();
		returnValue.setOperationName(RequestOperationaName.DELETE.name());
		
		bookService.deleteBook(id);
		
		returnValue.setOperationResult(RequestOperationaName.SUCCESS.name());
		
		return returnValue;
	}
}
