package com.personiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String message;
	private String status;
	private Object data;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
}
