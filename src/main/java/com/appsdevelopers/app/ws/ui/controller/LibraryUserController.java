package com.appsdevelopers.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.service.UserService;
import com.appsdevelopers.app.ws.ui.model.request.LibraryUserDetailsRequestModel;
import com.appsdevelopers.app.ws.ui.model.response.ErrorMessages;
import com.appsdevelopers.app.ws.ui.model.response.LibraryUserResponse;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/libraryUsers")
public class LibraryUserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService listOfBooksService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping()
	public String getUser() {
		
		return "User information fetched" ;
	}

	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
		     produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public LibraryUserResponse createUser(@Valid @RequestBody LibraryUserDetailsRequestModel userDetails) throws Exception {
		
		LibraryUserResponse returnValue=new LibraryUserResponse();
		if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserDto userDto=mapper.map(userDetails,UserDto.class);
		UserDto createdUser=userService.createUser(userDto);
		returnValue =mapper.map(createdUser,LibraryUserResponse.class);	
		
		return  returnValue ;
	}
	
	@PutMapping()
	public String updateUser() {
				
		return "User information updated" ;
	}
	
	@DeleteMapping()
	public String deleteUser() {
		
		return "User information deleted" ;
	}
}
