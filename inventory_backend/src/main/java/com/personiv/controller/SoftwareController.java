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

import com.personiv.dao.SoftwareDao;
import com.personiv.model.assets.Software;
import com.personiv.model.assets.SystemUnitSoftware;
import com.personiv.service.SoftwareService;

@RestController
@CrossOrigin(origins = "*")
public class SoftwareController {
	
	@Autowired
	private SoftwareService softService;
	
	@RequestMapping(value = "/software", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public List<Software> getSoftwares(){
		return softService.getSoftwares();
	}
	
	@RequestMapping(value = "/software", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> addSoftware(@RequestBody Software software){
		softService.addSoftware(software);
		return ResponseEntity.ok(software);
	}
	
	@RequestMapping(value = "/software", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> updateSoftware(@RequestBody Software software){
		softService.updateSoftware(software);
		return ResponseEntity.ok(software);
	}
	@RequestMapping(value = "/installed_software", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemUnitSoftware> getSystemUnitSoftwares(){
		return softService.getSystemUnitSoftwares();
	}
	@RequestMapping(value = "/installed_software", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSystemUnitSoftwares(@RequestBody SystemUnitSoftware sus){
		
		softService.addSystemUnitSoftware(sus);
		return ResponseEntity.ok(sus);
	}
	
	@RequestMapping(value = "/installed_software", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSystemUnitSoftware(@RequestBody SystemUnitSoftware sus){
		
		softService.updateSystemUnitSoftware(sus);
		return ResponseEntity.ok(sus);
	}
	@RequestMapping(value = "/installed_software/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteSystemUnitSoftware(@RequestBody SystemUnitSoftware sus){
		
		softService.deleteSystemUnitSoftware(sus);
		return ResponseEntity.ok(sus);
	}
}
