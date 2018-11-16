package com.personiv.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Supplier {
	private Long id;
	private String supplierName;
	private String contactPerson;
	private String contactNumber;
	private String email;
	private Date createdAt;
	private Date updatedAt;
}
