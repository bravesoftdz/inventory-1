package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.UPS;
import com.personiv.model.assets.Model;

public class UPSRowMapper implements RowMapper<UPS>{

	@Override
	public UPS mapRow(ResultSet rs, int arg1) throws SQLException {
		

		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		UPS up = new UPS();
		
		up.setId(rs.getLong("upId"));
		up.setDescription(rs.getString("upDesc"));
		up.setSerialNumber(rs.getString("upSerial"));
		up.setAssetNumber(rs.getString("upAsset"));
		up.setStatus(rs.getString("upStatus"));
		
		up.setModel(m);
		up.setWarrantyExp(rs.getDate("upWarranty"));
		up.setCreatedAt(rs.getTimestamp("upCreated"));
		up.setUpdatedAt(rs.getTimestamp("upUpdated"));
		
		return up;
	}

}
