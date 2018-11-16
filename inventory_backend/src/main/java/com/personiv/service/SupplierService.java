package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.SupplierDao;
import com.personiv.model.Supplier;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierDao supplierDao;
	
	public List<Supplier> getSuppliers(){
		return  supplierDao.getSuppliers();
	}
	
	public Supplier getSupplier(Long id) {
		return supplierDao.getSupplier(id);
	}
}
