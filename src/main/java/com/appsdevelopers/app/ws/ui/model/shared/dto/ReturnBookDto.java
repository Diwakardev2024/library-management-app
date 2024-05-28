package com.appsdevelopers.app.ws.ui.model.shared.dto;

import java.util.List;

import com.appsdevelopers.app.ws.entity.BookShelfEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReturnBookDto {

	@NotNull
	private UserDto UserId;
	
	@NotNull
	private List<BookShelfEntity> serialNo;
	
	
}
