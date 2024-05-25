package com.appsdevelopers.app.ws.ui.model.response;

import lombok.Data;

@Data
public class LibraryBookResponse {
	
	private long id;
	private String bookId;
	private String title;
	private String Author;
	private String ISBN;
	private Float price;
	private Long count;

}
