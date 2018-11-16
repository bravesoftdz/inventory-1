package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.ItdAssetDao;
import com.personiv.model.assets.ItdAsset;
import com.personiv.model.locations.ItdRoom;

@Service
public class ItdAssetService {

	@Autowired
	private ItdAssetDao itdAssetDao;
	
	public List<ItdAsset> getItdAssets(){
		return itdAssetDao.getItdAssets();
	}

	public void addITDAsset(ItdAsset asset) {
		itdAssetDao.addITDAsset(asset);
		
	}
	
	public List<ItdRoom> getITDRooms(){
		return itdAssetDao.getITDRooms();
	}
	
}
