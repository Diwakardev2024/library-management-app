package com.appsdevelopers.app.ws.ui.model.shared.dto;

import java.util.List;

import com.appsdevelopers.app.ws.entity.BookShelfEntity;

import lombok.Data;

@Data
public class IssueBookDto {
	
	private UserDto user;
	private List<BookShelfEntity> bookShelfEntities;
	

}
