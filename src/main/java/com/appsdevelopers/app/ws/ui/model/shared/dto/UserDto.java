package com.appsdevelopers.app.ws.ui.model.shared.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 4315285507123528767L;
	
//	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobileNo;
}
