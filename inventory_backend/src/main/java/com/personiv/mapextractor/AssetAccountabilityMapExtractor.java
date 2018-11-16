package com.personiv.mapextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.personiv.dao.Employee;
import com.personiv.model.Accountability;
import com.personiv.model.AssetAccountabilities;
import com.personiv.model.RawAsset;

public class AssetAccountabilityMapExtractor implements ResultSetExtractor <List<AssetAccountabilities>> {

	
	
	@Override
	public List<AssetAccountabilities> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long,AssetAccountabilities> accMap = new HashMap<>();
		
	
		
		while(rs.next()) {
			Long accId = rs.getLong("acc_id");
			String empName = rs.getString("emp_name");
			String empNumber = rs.getString("emp_id");
			
			
			
			
			AssetAccountabilities acc = accMap.get(accId);
			
			Accountability asset = new Accountability();
			Long assAccId = rs.getLong("as_acc_id");
			String assetNumber = rs.getString("asset_number");
			String serialNumber = rs.getString("serial_number");
			String assetType = rs.getString("asset_type");
			String attachment = rs.getString("attachment");
			String remarks = rs.getString("remarks");
			
			asset.setId(assAccId);
			asset.setAssetNumber(assetNumber);
			asset.setSerialNumber(serialNumber);
			asset.setAssetType(assetType);
			asset.setAttachment(attachment);
			asset.setRemarks(remarks);
			asset.setUpdatedAt(rs.getTimestamp("updated_at"));
			asset.setCreatedAt(rs.getTimestamp("created_at"));
			
			//Asset accountability has not encountered this id
			if(acc == null) {
				 acc = new AssetAccountabilities();
				 acc.setId(accId);
				 acc.setEmployeeName(empName);
				 acc.setEmployeeNumber(empNumber);
				 
				accMap.put(accId, acc);
			}
			
			List<Accountability> accountabilities = acc.getAccountabilities();
			
			if(accountabilities == null) {
				accountabilities = new ArrayList<>();
				acc.setAccountabilities(accountabilities);
			}
			
			if(assetNumber != null)
				accountabilities.add(asset);
			
			
		}
		
		return new ArrayList<AssetAccountabilities>(accMap.values());
	}

}
