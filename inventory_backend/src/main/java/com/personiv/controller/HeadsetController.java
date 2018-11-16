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
import com.personiv.model.assets.Headset;
import com.personiv.service.HeadsetService;

@RestController
@CrossOrigin(origins = "*")
public class HeadsetController {
	
	@Autowired
	private HeadsetService headsetService;
	
	@RequestMapping(value = {"/headsets","/headsets/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Headset> getHeadsets() {
		return headsetService.getHeadsets();
	}
	@RequestMapping(value = {"/headsets/available","/headsets/available/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Headset> getAvailableHeadsets() {
		return headsetService.getAvailableHeadsets();
	}
	
	@RequestMapping(value = {"/headsets"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHeadset(@RequestBody Headset keyboard) {
		
		try {
			headsetService.updateHeadset(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	@RequestMapping(value = {"/headsets"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addHeadset(@RequestBody Headset keyboard) {
		
		try {
			headsetService.addHeadset(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
	
	@RequestMapping(value = {"/headsets/delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteHeadset(@RequestBody Headset keyboard) {
		
		try {
			headsetService.deleteHeadset(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}

}
