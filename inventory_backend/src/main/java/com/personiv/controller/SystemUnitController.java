package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.assets.SystemUnit;
import com.personiv.service.SystemUnitService;

@RestController
@CrossOrigin(origins = "*")
public class SystemUnitController {
	@Autowired
	private SystemUnitService systemUnitService;
	
	@RequestMapping(value = {"/system-units","/system-units/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemUnit> getSystemUnits() {
		return systemUnitService.getSystemUnits();
	}
	@RequestMapping(value = "/system-units/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SystemUnit getSystemUnit(@PathVariable("id")Long id) {
		return systemUnitService.getSystemUnit(id);
	}
	
	@RequestMapping(value = "/system-units/getByAssetNumber/{tag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SystemUnit getSystemUnit(@PathVariable("tag")String assetTag) {
		return systemUnitService.getSystemUnitByAssetTag(assetTag);
	}
	
	@RequestMapping(value = {"/system-units/available_system_units","/system-units/available_system_units/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemUnit> getAvailableSystemUnits() {
		return systemUnitService.getAvailableSystemUnits();
	}
	
	@RequestMapping(value = {"/system-units/group_by_department"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSystemUnitsGroupByDepartment() {
		return ResponseEntity.ok(systemUnitService.getSystemUnitsGroupByDepartment());
	}
	
	@RequestMapping(value = {"/system-units/group_by_model"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSystemUnitsGroupByModel() {
		return ResponseEntity.ok(systemUnitService.getSystemUnitsGroupByModel());
	}
	
	@RequestMapping(value = {"/system-units/chart_by_model"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSystemUnitsChartByModel() {
		return ResponseEntity.ok(systemUnitService.getSystemUnitsChartByModel());
	}
	
	@RequestMapping(value = {"/system-units"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSystemUnit(@RequestBody SystemUnit systemUnit) {
		try {
			systemUnitService.addSystemmUnit(systemUnit);
			return ResponseEntity.ok(systemUnit);
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
	
	
	
	@RequestMapping(value = {"/system-units"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSystemUnit(@RequestBody SystemUnit systemUnit) {
		
		try {
			systemUnitService.updateSystemUnit(systemUnit);
			return ResponseEntity.ok(systemUnit);
			
		}catch(DuplicateKeyException e) {
			return ResponseEntity
					.status(500)
					.body(new ErrorResponse("Duplicate entry"));
		}
	}
}
