package com.appsdevelopers.app.ws.ui.model.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IssueBookRequestModel {
	
	@NotNull // Bean validation
	private String userId;
	
	@NotNull
	@Size(min = 1,message = "BookId should be at least 1")
	private List<String> bookIds;

}
