package com.personiv.model;

import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.SystemUnit;

import lombok.Data;

@Data
public class NonProdMonitor {
	private Long id;
	private Monitor monitor;
}
