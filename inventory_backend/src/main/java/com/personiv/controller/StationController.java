package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.locations.Station;
import com.personiv.service.StationService;


@RestController
@CrossOrigin(origins = "*")
public class StationController {
	
	@Autowired
	private StationService stationService;
	
	
	@RequestMapping(value = {"/stations","/stations/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStations() {
		
		List<Station> stations = stationService.getStations();
		return ResponseEntity.ok(stations);
	}
	
	@RequestMapping(value = "/stations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addStations(@RequestBody Station station) {
		
		stationService.addStation(station);
		return ResponseEntity.ok(station);
	}
	
	@RequestMapping(value = "/stations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStations(@RequestBody Station station) {
		
		stationService.updateStation(station);
		return ResponseEntity.ok(station);
	}
}
