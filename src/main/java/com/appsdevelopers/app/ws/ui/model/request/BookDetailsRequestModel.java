package com.appsdevelopers.app.ws.ui.model.request;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BookDetailsRequestModel {
	
	private String title;
	private String Author;
	private String ISBN;
	
	@Min(value = 1, message = "")
	private Float price;
	
	@Range(min = 1,max=500,message = "Count can't be negative")
	private long count=1;
	

}
