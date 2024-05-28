package com.appsdevelopers.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdevelopers.app.ws.entity.UserEntity;
import com.appsdevelopers.app.ws.repositories.UserRepository;
import com.appsdevelopers.app.ws.service.UserService;
import com.appsdevelopers.app.ws.shared.Utils;
import com.appsdevelopers.app.ws.ui.model.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto user) {

		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException(" Records already exists ");

		UserEntity userEntity = new UserEntity();
		mapper.map(user, userEntity);

		String publicUserId = utils.generatedUserId(30);
		userEntity.setUserId(publicUserId);
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();

		returnValue = mapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new RuntimeException("UserId not found");
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers() {
		
		List<UserDto> returnValue = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findAll();
		
		if (userEntities == null)
			throw new RuntimeException("Users not found");
		for(UserEntity entity:userEntities) {
			
			UserDto userDto=new UserDto();
			
			BeanUtils.copyProperties(entity, userDto);
			
			returnValue.add(userDto);
			
			
		}
		return returnValue;
		
	}

	@Override
	public UserDto validateUserId(String userId) {
		
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new RuntimeException("UserId is InValid.");
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;

	}


	
}
