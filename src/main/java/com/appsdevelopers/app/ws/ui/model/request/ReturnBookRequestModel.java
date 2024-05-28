package com.appsdevelopers.app.ws.ui.model.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReturnBookRequestModel {
	
	@NotNull // Bean validation
	private String userId;
	
	@NotNull
	@Size(min = 1,message = "Serial number should be matched at least 1")
	private List<String> serialNo;

}
