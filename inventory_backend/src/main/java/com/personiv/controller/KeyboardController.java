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
import com.personiv.model.assets.Keyboard;
import com.personiv.service.KeyboardService;

@RestController
@CrossOrigin(origins = "*")
public class KeyboardController {
	
	@Autowired
	private KeyboardService kbService;
	
	@RequestMapping(value = {"/keyboards","/keyboards/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Keyboard> getKeyboards() {
		return kbService.getKeyboards();
	}
	@RequestMapping(value = {"/keyboards/available","/keyboards/available/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Keyboard> getAvailableKeyboards() {
		return kbService.getAvailableKeyboards();
	}
	
	@RequestMapping(value = {"/keyboards"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateKeyboard(@RequestBody Keyboard keyboard) {
		
		try {
			kbService.updateKeyboard(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	@RequestMapping(value = {"/keyboards"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addKeyboard(@RequestBody Keyboard keyboard) {
		
		try {
			kbService.addKeyboard(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
	
	@RequestMapping(value = {"/keyboards/delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteKeyboard(@RequestBody Keyboard keyboard) {
		
		try {
			kbService.deleteKeyboard(keyboard);
			return ResponseEntity.ok(keyboard);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}

}
