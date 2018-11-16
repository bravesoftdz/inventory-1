package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.locations.ProductionArea;
import com.personiv.service.LocationService;

@RestController
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = {"/locations","/locations/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductionArea> getLocations() {
		return locationService.getLocations();
	}
	
	@RequestMapping(value = {"/locations/available_locations","/locations/avaliable_locations/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductionArea> getAvailableLocations() {
		return locationService.getAvailableLocations();
	}
	@RequestMapping(value = "/locations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductionArea getAsset(@PathVariable("id") Long id) {
		return locationService.getLocation(id);
	}
	
	@RequestMapping(value = "/location/searchLocation/{search}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductionArea> searchStations(@PathVariable("search") String search) {
		return locationService.searchStation(search);
	}

}
