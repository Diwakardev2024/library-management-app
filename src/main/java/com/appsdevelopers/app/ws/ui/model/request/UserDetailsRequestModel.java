package com.appsdevelopers.app.ws.ui.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDetailsRequestModel {
	
	@NotNull(message = "firstName can not be null")
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer mobileNo;
//	private List<ListOfBooksDto> listOfBooks;

}
