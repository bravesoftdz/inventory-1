package com.personiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.SwitchConfig;
import com.personiv.model.SwitchPortConfig;
import com.personiv.service.SwitchConfigService;

@RestController
@CrossOrigin(origins = "*")
public class SwitchConfigController {
	
	@Autowired
	private SwitchConfigService switchConfService;
	
	@RequestMapping(value = "/switch-configs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSwitchConfigs() {
		
		return ResponseEntity.ok(switchConfService.getSwitchConfigs());
	}
	
	@RequestMapping(value = "/switch-configs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSwitchConfig(@RequestBody SwitchConfig config) {
		
		switchConfService.addSwitchConfig(config);
		return ResponseEntity.ok(config);
	}
	
	@RequestMapping(value = "/switch-configs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSwitchConfig(@RequestBody SwitchPortConfig config) {
		switchConfService.updateSwitchConfig(config);
		return ResponseEntity.ok(config);
	}
}
