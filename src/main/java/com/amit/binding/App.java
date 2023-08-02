package com.amit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class App {

	private Long caseNum;

	private String fullname;

	private String email;

	private String phno;

	private String gender;

	private LocalDate dob;

	private Long ssn;
	
	private Integer userId;
}
