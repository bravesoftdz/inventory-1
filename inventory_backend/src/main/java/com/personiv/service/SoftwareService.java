package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.SoftwareDao;
import com.personiv.model.assets.Software;
import com.personiv.model.assets.SystemUnitSoftware;

@Service
public class SoftwareService {
	
	@Autowired
	private SoftwareDao softDao;
	
	public List<Software> getSoftwares(){
		return softDao.getSoftwares();
	}
	
	public List<SystemUnitSoftware> getSystemUnitSoftwares(){
		return softDao.getSystemUnitSoftwares();
	}

	public void addSoftware(Software software) {
		softDao.addSoftware(software);
		
	}

	public void updateSoftware(Software software) {
		softDao.updateSoftware(software);
	}

	public void addSystemUnitSoftware(SystemUnitSoftware sus) {
		softDao.addSystemUnitSoftware(sus);
		
	}

	public void updateSystemUnitSoftware(SystemUnitSoftware sus) {
		softDao.updateSystemUnitSoftware(sus);
		
		
	}

	public void deleteSystemUnitSoftware(SystemUnitSoftware sus) {
		softDao.deleteSystemUnitSoftware(sus);
		
	}
}
