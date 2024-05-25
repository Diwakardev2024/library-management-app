package com.appsdevelopers.app.ws.ui.model.shared.dto;


import lombok.Data;

@Data
public class BookDto {

	private long id;
	private String bookId;
	private String title;
	private String Author;
	private String ISBN;
	private Float price;
	private UserDto userDetails;
	private Long count;

}
