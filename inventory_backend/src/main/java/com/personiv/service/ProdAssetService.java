package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.ProdAssetDao;
import com.personiv.model.assets.ProdAssetStaging;
import com.personiv.model.assets.ProdTransferStaging;
import com.personiv.model.assets.ProductionAsset;
import com.personiv.model.assets.TransferAsset;

@Service
public class ProdAssetService {
	
	@Autowired
	private ProdAssetDao aslDao;
	
	
	public List<ProdAssetStaging> getProdAssets(){
		return aslDao.getProdAssets();
	}
	public List<ProductionAsset> getProductionAssets(){
		return aslDao.getProductionAssets();
	}

	public void addProductionAsset(ProductionAsset prodAsset) {
		aslDao.addProductionAsset(prodAsset);
		
	}

	public void updateProductionAsset(ProductionAsset prodAsset) {
		aslDao.updateProductionAsset(prodAsset);
		
	}

	public void removeMonitor1(ProductionAsset prodAsset) {
		aslDao.removeMonitor1(prodAsset);
		
	}
	public void removeMonitor2(ProductionAsset prodAsset) {
		aslDao.removeMonitor2(prodAsset);
		
	}

	
	public void batchUpdate(List<ProductionAsset> assets) {
		aslDao.batchUpdate(assets);
	}
	
	public void batchTransferUpdate(List<TransferAsset> transferAssets) {
		aslDao.batchTransferUpdate(transferAssets);
	}
	
	public List<ProductionAsset> batchUpload(List<ProdAssetStaging> assets){
		return aslDao.batchUpload(assets);
	}

	
	public List<TransferAsset>  batchTransferUpload(List<ProdTransferStaging> prodAssets) {
		return aslDao.batchTransferUpload(prodAssets);
	}
}
