package com.appsdevelopers.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.UserRepository;
import com.appsdevelopers.app.ws.entity.ListOfBooksEntity;
import com.appsdevelopers.app.ws.entity.UserEntity;
import com.appsdevelopers.app.ws.service.UserService;
import com.appsdevelopers.app.ws.shared.Utils;
import com.appsdevelopers.app.ws.ui.model.shared.dto.ListOfBooksDto;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepository.findByEmail(user.getEmail())!=null)
			throw new RuntimeException(" Records already exists ");
		
		for(int i=0;i<user.getBooks().size();i++) {
			
			ListOfBooksDto listOfBooksDto=user.getBooks().get(i);
			listOfBooksDto.setUserDetails(user);
			listOfBooksDto.setBookId(utils.generatedBookId(30));
			user.getBooks().set(i, listOfBooksDto);
		}
		
		UserEntity userEntity = new UserEntity();
		mapper.map(user, userEntity);
		
		List<ListOfBooksEntity> listOfBookEntity=new ArrayList<>();
		
		userEntity.setBooks(listOfBookEntity);
		
		String publicUserId = utils.generatedUserId(30);
		userEntity.setUserId(publicUserId);
		UserEntity storedUserDetails=userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		
		returnValue=mapper.map(storedUserDetails, UserDto.class);
		
		return returnValue;
	}

}
