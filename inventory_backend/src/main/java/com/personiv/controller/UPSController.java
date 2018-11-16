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
import com.personiv.model.assets.UPS;
import com.personiv.service.UPSService;

@RestController
@CrossOrigin(origins = "*")
public class UPSController {
	@Autowired
	private UPSService upsService;
	
	@RequestMapping(value = {"/ups","/ups/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UPS> getUPSs() {
		return upsService.getUPSs();
	}
	@RequestMapping(value = {"/ups/available_ups","/ups/available/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UPS> getAvailableUPSs() {
		return upsService.getAvailableUPS();
	}
	
	@RequestMapping(value = {"/ups"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUPS(@RequestBody UPS ups) {
		
		try {
			upsService.updateUPS(ups);
			return ResponseEntity.ok(ups);
		}catch(DuplicateKeyException e) {
			
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
	@RequestMapping(value = {"/ups"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUPS(@RequestBody UPS ups) {
		try {
			upsService.addUPS(ups);
			return ResponseEntity.ok(ups);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
}
