package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.assets.ItdAsset;
import com.personiv.service.ItdAssetService;

@RestController
public class ItdAssetController {
	@Autowired
	private ItdAssetService itdService;
	
	@RequestMapping(value = "/it_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ItdAsset> getItdAssets() {
		return itdService.getItdAssets();
	}
	
	@RequestMapping(value = "/it_assets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getItdAssets(@RequestBody ItdAsset asset) {
		itdService.addITDAsset(asset);
		return ResponseEntity.ok(asset);
	}
	
	@RequestMapping(value = "/it_rooms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getITDRooms() {
		
		return ResponseEntity.ok(itdService.getITDRooms());
	}
}
