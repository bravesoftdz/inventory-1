package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.assets.Monitor;
import com.personiv.service.MonitorService;

@RestController
@CrossOrigin(origins = "*")
public class MonitorController {
	@Autowired
	private MonitorService monitorService;
	
	@RequestMapping(value = {"/monitors","/monitors/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Monitor> getMonitors() {
		return monitorService.getMonitors();
	}
	@RequestMapping(value = {"/monitors/available_monitors","/monitors/available_monitors/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Monitor> getAvailableMonitors() {
		return monitorService.getAvailableMonitors();
	}
	
	@RequestMapping(value = {"/monitors"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMonitor(@RequestBody Monitor monitor) {
		
		try {
			monitorService.updateMonitor(monitor);
			return ResponseEntity.ok(monitor);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	@RequestMapping(value = {"/monitors"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMonitor(@RequestBody Monitor monitor) {
		
		try {
			monitorService.addMonitor(monitor);
			return ResponseEntity.ok(monitor);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
}
