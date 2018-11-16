package com.personiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.NonProdLocation;
import com.personiv.service.NonProdLocationService;

@RestController
@CrossOrigin(origins = "*")
public class NonProdLocationController {
	
	@Autowired
	private NonProdLocationService npService;
	
	@RequestMapping(value = "/non_prod_locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProdNonLocations() {
		return ResponseEntity.ok(npService.getNonProdLocations());
	}
	
	@RequestMapping(value = "/non_prod_locations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdLocation(@RequestBody NonProdLocation npLoc) {
		npService.addNonProdLocation(npLoc);
		return ResponseEntity.ok(npLoc);
	}
	
	@RequestMapping(value = "/non_prod_locations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateNonProdLocation(@RequestBody NonProdLocation npLoc) {
		npService.updateNonProdLocation(npLoc);
		return ResponseEntity.ok(npLoc);
	}
	
}
