package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.NonProdAssetDao;
import com.personiv.model.NonProdAssetLocation;
import com.personiv.model.NonProdCPUDeployed;
import com.personiv.model.NonProdKeyboardDeployed;
import com.personiv.model.NonProdMonitorDeployed;
import com.personiv.model.NonProdMouseDeployed;
import com.personiv.model.NonProdUPSDeployed;

@Service
public class NonProdAssetService {
	
	@Autowired
	private NonProdAssetDao npDao;
	
	
	public List<NonProdAssetLocation> getNonProdAssets(){
		return npDao.getNonProdAssets();
	}


	public void addNonProdAssets(NonProdAssetLocation npLocation) {
		npDao.addNonProdAssets(npLocation);
		
	}
	
	public void addNonProdCPU(NonProdCPUDeployed npCPU) {
		npDao.addNonProdCPU(npCPU);
		
	}
	
	public void addNonProdMonitor(NonProdMonitorDeployed npMonitor) {
		npDao.addNonProdMonitor(npMonitor);
	}
	
    public void addNonProdKeyboard(NonProdKeyboardDeployed npKeyboard) {
    	npDao.addNonProdKeyboard(npKeyboard);
    }
    
    public void addNonProdMouse(NonProdMouseDeployed npMouse) {
    	npDao.addNonProdMouse(npMouse);
    }
    
    public void addNonProdUPS(NonProdUPSDeployed npUPS) {
    	npDao.addNonProdUPS(npUPS);
    }
    
    public void deleteNonProdCPU(NonProdCPUDeployed npCPU) {
    	npDao.deleteNonProdCPU(npCPU);
    }
    
    public void deleteNonProdMonitor(NonProdMonitorDeployed npMonitor) {
    	npDao.deleteNonProdMonitor(npMonitor);
    }
     
    public void deleteNonProdKeyboard(NonProdKeyboardDeployed npKeyboard) {
    	npDao.deleteNonProdKeyboard(npKeyboard);
     }
     public void deleteNonProdMouse(NonProdMouseDeployed npMouse) {
    	 npDao.deleteNonProdMouse(npMouse);
     }
     
     public void deleteNonProdUPS(NonProdUPSDeployed npUPS) {
    	 npDao.deleteNonProdUPS(npUPS);
     }
}
