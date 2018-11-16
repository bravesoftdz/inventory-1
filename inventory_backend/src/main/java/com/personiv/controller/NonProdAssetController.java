package com.personiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.NonProdAssetLocation;
import com.personiv.model.NonProdCPUDeployed;
import com.personiv.model.NonProdKeyboardDeployed;
import com.personiv.model.NonProdMonitorDeployed;
import com.personiv.model.NonProdMouseDeployed;
import com.personiv.model.NonProdUPSDeployed;
import com.personiv.service.NonProdAssetService;


@RestController
@CrossOrigin(origins = "*")
public class NonProdAssetController {
	
	@Autowired
	private NonProdAssetService npService;
	
	@RequestMapping(value = "/non_prod_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProdNonAssets() {
		return ResponseEntity.ok(npService.getNonProdAssets());
	}
	
	@RequestMapping(value = "/non_prod_assets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProdNonAssets(@RequestBody NonProdAssetLocation npLocation) {
		npService.addNonProdAssets(npLocation);
		
		return ResponseEntity.ok(npService.getNonProdAssets());
	}	
	
	@RequestMapping(value = "/non_prod_assets/add_cpu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdCPU(@RequestBody NonProdCPUDeployed npCPU) {
		npService.addNonProdCPU(npCPU);
		
		return ResponseEntity.ok(npCPU);
	}
	
	@RequestMapping(value = "/non_prod_assets/add_monitor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdMonitor(@RequestBody NonProdMonitorDeployed npMonitor) {
		npService.addNonProdMonitor(npMonitor);
		return ResponseEntity.ok(npMonitor);
	}
	
	@RequestMapping(value = "/non_prod_assets/add_keyboard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdKeyboard(@RequestBody NonProdKeyboardDeployed npKeyboard) {
		npService.addNonProdKeyboard(npKeyboard);
		return ResponseEntity.ok(npKeyboard);
	}
	
	@RequestMapping(value = "/non_prod_assets/add_mouse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdMouse(@RequestBody NonProdMouseDeployed npMouse) {
		npService.addNonProdMouse(npMouse);
		return ResponseEntity.ok(npMouse);
	}
	
	@RequestMapping(value = "/non_prod_assets/add_ups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNonProdKeyboard(@RequestBody NonProdUPSDeployed npUps) {
		npService.addNonProdUPS(npUps);
		return ResponseEntity.ok(npUps);
	}
	
	@RequestMapping(value = "/non_prod_assets/delete_cpu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteNonProdCPU(@RequestBody NonProdCPUDeployed npCPU) {
		npService.deleteNonProdCPU(npCPU);
		
		return ResponseEntity.ok(npCPU);
	}
	
	@RequestMapping(value = "/non_prod_assets/delete_monitor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteNonProdMonitor(@RequestBody NonProdMonitorDeployed npMonitor) {
		npService.deleteNonProdMonitor(npMonitor);
		return ResponseEntity.ok(npMonitor);
	}
	
	@RequestMapping(value = "/non_prod_assets/delete_keyboard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteNonProdKeyboard(@RequestBody NonProdKeyboardDeployed npKeyboard) {
		npService.deleteNonProdKeyboard(npKeyboard);
		return ResponseEntity.ok(npKeyboard);
	}
	
	@RequestMapping(value = "/non_prod_assets/delete_mouse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteNonProdMouse(@RequestBody NonProdMouseDeployed npMouse) {
		npService.deleteNonProdMouse(npMouse);
		return ResponseEntity.ok(npMouse);
	}
	
	@RequestMapping(value = "/non_prod_assets/delete_ups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteNonProdUPS(@RequestBody NonProdUPSDeployed npUps) {
		npService.deleteNonProdUPS(npUps);
		return ResponseEntity.ok(npUps);
	}
}
