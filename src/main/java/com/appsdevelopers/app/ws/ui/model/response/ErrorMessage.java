package com.appsdevelopers.app.ws.ui.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {
	
	private Date timestamp;
	private String message;
	
	public ErrorMessage(Date timestamp,String message) {
		
		this.timestamp=timestamp;
		this.message=message;
	}
	

}
