package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.RFIDDao;
import com.personiv.model.assets.RFID;
@Service
public class RFIDService {

	@Autowired
	private RFIDDao rfidDao;
	
	public List<RFID> getRFIDs(){
		return rfidDao.getRFIDs();
	}
	
	public RFID getRFIDbyId(Long id){
		return rfidDao.getRFIDbyId(id);
	}
	
	public RFID getRFIDbyAssetNumber(String assetNumber){
		return rfidDao.getRFIDbyAssetNumber(assetNumber);

	}

	public void addRFID(RFID rfid) {
		rfidDao.addRFID(rfid);
		
	}

	public void updateRFID(RFID rfid) {
		rfidDao.updateRFID(rfid);
		
	}
	
}
