package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.Accountability;
import com.personiv.model.AssetAccountable;

public class AssetAccountableRowMapper implements RowMapper<AssetAccountable>{

	@Override
	public AssetAccountable mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AssetAccountable acc = new AssetAccountable();
		acc.setAccId(rs.getLong("accId"));
		acc.setEmployeeName(rs.getString("employeeName"));
		acc.setEmployeeNumber(rs.getString("employeeNumber"));
		
		Accountability act = new Accountability();
		act.setId(rs.getLong("actId"));
		act.setAssetNumber(rs.getString("assetNumber"));
		act.setSerialNumber(rs.getString("serialNumber"));
		act.setAssetType(rs.getString("assetType"));
		act.setAttachment(rs.getString("attachment"));
		act.setRemarks(rs.getString("remarks"));
		act.setCreatedAt(rs.getTimestamp("createdAt"));
		act.setUpdatedAt(rs.getTimestamp("updatedAt"));
		acc.setAccountability(act);
		return acc;
	}

}
