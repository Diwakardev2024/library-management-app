package com.appsdevelopers.app.ws.ui.model.shared.dto;


import lombok.Data;

@Data
public class BookDto {

	private long id;
	private String bookId;
	private String title;
	private String Author;
	private String Isbn_Number;
	private Float price;
	private UserDto userDetails;

}
