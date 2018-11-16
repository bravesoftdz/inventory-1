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
import com.personiv.model.assets.Mouse;
import com.personiv.service.MouseService;

@RestController
@CrossOrigin(origins = "*")
public class MouseController {
	@Autowired
	private MouseService mouseService;
	
	@RequestMapping(value = {"/mice","/mice/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Mouse> getMouses() {
		return mouseService.getMouses();
	}
	@RequestMapping(value = {"/mice/available","/mice/available/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Mouse> getAvailableMouses() {
		return mouseService.getAvailableMouses();
	}
	
	@RequestMapping(value = {"/mice"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMouse(@RequestBody Mouse mouse) {
		
		try {
			mouseService.updateMouse(mouse);
			return ResponseEntity.ok(mouse);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
	@RequestMapping(value = {"/mice"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMouse(@RequestBody Mouse mouse) {
		
		try {
		
			mouseService.addMouse(mouse);
			return ResponseEntity.ok(mouse);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
				.status(500)
				.body(new ErrorResponse("Duplicate entry"));
		}
	}
}