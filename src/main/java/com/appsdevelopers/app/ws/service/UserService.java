package com.appsdevelopers.app.ws.service;


import java.util.List;

import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto getUserByUserId(String userId);
	List<UserDto> getUsers(); 
}
