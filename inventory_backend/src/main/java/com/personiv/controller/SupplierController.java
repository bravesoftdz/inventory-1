package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.Supplier;
import com.personiv.service.SupplierService;

@RestController
@CrossOrigin(origins = "*")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = "/suppliers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Supplier> getAssets() {
		return supplierService.getSuppliers();
	}
	
	@RequestMapping(value = "/suppliers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Supplier getAsset(@PathVariable("id") Long id) {
		return supplierService.getSupplier(id);
	}
}
