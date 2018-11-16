package com.personiv.model;

import lombok.Data;

@Data
public class PrintableDocument {
	private String contentType;
	private byte[] content;
	
	
}
