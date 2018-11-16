package com.personiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.EmptyResponse;
import com.personiv.model.assets.RFID;
import com.personiv.service.RFIDService;

@RestController
@CrossOrigin(origins = "*")
public class RFIDController {
	
	@Autowired
	private RFIDService rfidService;
	
	@RequestMapping(value = "/rfids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRFIDs() {
		return ResponseEntity.ok(rfidService.getRFIDs());
	}
	
	@RequestMapping(value = "/rfids", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRFID(@RequestBody RFID rfid) {
		
		rfidService.addRFID(rfid);
		return ResponseEntity.ok(rfid);
	}
	
	@RequestMapping(value = "/rfids", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRFID(@RequestBody RFID rfid) {
		
		rfidService.updateRFID(rfid);
		return ResponseEntity.ok(rfid);
	}
	@RequestMapping(value = "/rfids/byAssetNumber/{assetNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRFIDbyAssetNumber(@PathVariable("assetNumber")String assetNumber) {
		return ResponseEntity.ok(rfidService.getRFIDbyAssetNumber(assetNumber));
	}
	
	@RequestMapping(value = "/rfids/byId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRFIDbyId(@PathVariable("id")Long id) {
		
		try {

			return ResponseEntity.ok(rfidService.getRFIDbyId(id));
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.ok(new EmptyResponse());
		}
	}
}
