package com.amit.exceptions;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AppException {

	private String exCode;
	
	private String exDesc;
	
	private LocalDate exDate;
}
