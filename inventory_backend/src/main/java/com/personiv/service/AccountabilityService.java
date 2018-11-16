package com.personiv.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.dao.AccountabilityDao;
import com.personiv.model.Accountability;
import com.personiv.model.AssetAccountabilities;
import com.personiv.model.AssetAccountable;
import com.personiv.model.RawAsset;

@Service
public class AccountabilityService {
	
	@Autowired
	private AccountabilityDao accDao;
	
	public List<AssetAccountabilities> getAccountabilities(){
		return accDao.getAccountabilities();
	}

	public void addAccountability(Accountability acc) {
		accDao.addAccountability(acc);
		
	}

	public void updateAccountable(AssetAccountable acc) {
		accDao.updateAccountable(acc);
		
	}
	
	public void addAccountableAsset(AssetAccountable acc) {
		accDao.addAccountableAsset(acc);
	}

	public void deleteAccountable(AssetAccountable acc) {
		accDao.deleteAccountable(acc);
		
	}

	public void addAccountable(AssetAccountable acc) {
		accDao.addAccountableAsset(acc);
		
	}
	/**
	 * 
	 * @return
	 * List of Assets in raw form i.e. only asset number and type is fetched
	 */
	public List<RawAsset> getAllAssets() {
		return accDao.getAllAssets();
	}

	public AssetAccountable getAssetAccountableById(Long id) {
		
		return accDao.getAssetAccountableById(id);
	}

	public void uploadAttachment(Accountability acc, MultipartFile file) throws IOException {
		accDao.uploadAttachment(acc,file);
		
	}

	public Resource downloadAttachment(String filename) {
		// TODO Auto-generated method stu
		return accDao.downloadAttachment(filename);
	}

	public void updateRemarks(Accountability acc) {
		accDao.updateRemarks(acc);
		
	}
}
