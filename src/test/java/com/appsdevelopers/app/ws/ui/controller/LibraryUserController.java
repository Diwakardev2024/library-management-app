package com.appsdevelopers.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.appsdevelopers.app.ws.service.UserService;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

class LibraryUserController {
	
	@InjectMocks
	LibraryUserController libraryUserController;
	
	@Mock
	UserService userService;

	UserDto userDto;
	
	final String USER_ID="8e4prNGRnlEIOayRfl3I4Ye14TbG2I";
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		
		
		
	}

	

	@Test
	void testGetUserByUserId() {
		fail("Not yet implemented");
	}

	

}
