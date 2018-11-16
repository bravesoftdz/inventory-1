package com.personiv.model;

import java.util.List;

import lombok.Data;

@Data
public class AssetAccountabilities {
	private Long id;
	private String employeeName;
	private String employeeNumber;
	private List<Accountability> accountabilities;
}
