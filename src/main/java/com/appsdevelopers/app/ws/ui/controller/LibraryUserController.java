package com.appsdevelopers.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdevelopers.app.ws.LibraryManagementAppApplication;
import com.appsdevelopers.app.ws.service.BookService;
import com.appsdevelopers.app.ws.service.UserService;
import com.appsdevelopers.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdevelopers.app.ws.ui.model.response.ErrorMessages;
import com.appsdevelopers.app.ws.ui.model.response.LibraryUserResponse;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/library")
public class LibraryUserController {

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier(value = "offline-book")
	BookService listOfBooksService;

	@Autowired
	ModelMapper mapper;
	 
	 private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LibraryManagementAppApplication.class);

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public LibraryUserResponse getUserByUserId(@PathVariable String id) {
		
		 
		LibraryUserResponse returnValue = new LibraryUserResponse();
		UserDto userDto = userService.getUserByUserId(id);
		returnValue = mapper.map(userDto, LibraryUserResponse.class);

		return returnValue;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE  })
	public List<LibraryUserResponse> getUsers() {
		
		logger.info("Execution of GetUsers method started");
		
		List<LibraryUserResponse> returnValue = new ArrayList<>();
		List<UserDto> userDtos = userService.getUsers();
		
		if(logger.isDebugEnabled()) {
			logger.debug("List of userDtos:",userDtos);
		}
		for (UserDto userdto : userDtos) {

			LibraryUserResponse userResponse = new LibraryUserResponse();
			BeanUtils.copyProperties(userdto, userResponse);
			returnValue.add(userResponse);

		}
		logger.debug("List of userDtos:{} {}",userDtos,returnValue);
		// returnValue = mapper.map(userDto, LibraryUserResponse.class);
		logger.info("Execution of GetUsers method completed");
		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public LibraryUserResponse createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
			throws Exception {

		LibraryUserResponse returnValue = new LibraryUserResponse();
		if (userDetails.getFirstName().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserDto userDto = mapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		returnValue = mapper.map(createdUser, LibraryUserResponse.class);

		return returnValue;
	}
	
	
	@PutMapping()
	public String updateUser() {

		return "User information updated";
	}

	@DeleteMapping()
	public String deleteUser() {

		return "User information deleted";
	}
}
