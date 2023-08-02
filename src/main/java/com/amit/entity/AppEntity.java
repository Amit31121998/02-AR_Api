package com.amit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="IES_APPS")
public class AppEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long caseNum;

	private String fullname;

	private String email;

	private String gender;

	private LocalDate dob;

	private Long phno;

	private Long ssn;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
}
