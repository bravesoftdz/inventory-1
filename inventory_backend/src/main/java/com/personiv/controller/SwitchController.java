package com.personiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.Switch;
import com.personiv.service.SwitchService;

@RestController
@CrossOrigin(origins = "*")
public class SwitchController {

	
	@Autowired
	private SwitchService switchService;
	
	
	@RequestMapping(value = {"/switches","/switches/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSwitches() {
		return ResponseEntity.ok(switchService.getSwitches());
	}
	
	@RequestMapping(value = "/switches/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSwitch(@PathVariable("id") Long id) {
		return ResponseEntity.ok(switchService.getSwitch(id));
	}
	
	
	@RequestMapping(value = "/switches", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSwitch(@RequestBody Switch sw) {
		
		switchService.addSwitch(sw);
		return ResponseEntity.ok(sw);
	}
	@RequestMapping(value = "/switches", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editSwitch(@RequestBody Switch sw) {
		
		switchService.editSwitch(sw);
		return ResponseEntity.ok(sw);
	}
	
	@RequestMapping(value = "/switches/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteSwitch(@RequestBody Switch sw) {
		
		switchService.deleteSwitch(sw.getId());
		return ResponseEntity.ok(sw);
	}
}
