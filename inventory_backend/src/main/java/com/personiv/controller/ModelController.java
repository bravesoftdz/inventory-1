package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.assets.Model;
import com.personiv.service.ModelService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
public class ModelController {
	
	@Autowired
	private ModelService modelService;
	
	@RequestMapping(value = {"/models","/models/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Model> getLocations() {
		return modelService.getModels();
	}
	
	@RequestMapping(value = {"/models"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addModel(@RequestBody Model model) {
		
		try {

			modelService.addModel(model);
			return ResponseEntity.ok(model);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	
	@RequestMapping(value = {"/models"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateModel(@RequestBody Model model) {
		
		
		try {

			modelService.updateModel(model);
			return ResponseEntity.ok(model);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	
	@RequestMapping(value = {"/models/delete"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteModel(@RequestBody Model model) {
		
		try {
			modelService.deleteModel(model);
			return ResponseEntity.ok(model);
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Cannot delete, this record has an associated record","500",model));
		}
	}
}
