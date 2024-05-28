package com.appsdevelopers.app.ws.ui.model.response;

import java.util.List;

import com.appsdevelopers.app.ws.entity.BookShelfEntity;

import lombok.Data;

@Data
public class ReturnBookResponse {
	
	private String userId;
	private List<BookShelfEntity> bookShelfEntities;
	

}
