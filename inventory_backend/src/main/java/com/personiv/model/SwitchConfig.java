package com.personiv.model;

import java.util.List;

import lombok.Data;

@Data
public class SwitchConfig {
	private Long id;
	private Switch source;
	private List<PortConfig> portConfigs;
}
