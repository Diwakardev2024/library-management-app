package com.appsdevelopers.app.ws.ui.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryUserResponse {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer mobileNo;

}
