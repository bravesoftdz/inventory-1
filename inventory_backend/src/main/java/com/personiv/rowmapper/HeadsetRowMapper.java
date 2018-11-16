package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Headset;
import com.personiv.model.assets.Model;

public class HeadsetRowMapper implements RowMapper<Headset>{

	@Override
	public Headset mapRow(ResultSet rs, int arg1) throws SQLException {
		

		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		Headset hs = new Headset();
		
		hs.setId(rs.getLong("hsId"));
		hs.setDescription(rs.getString("hsDesc"));
		hs.setSerialNumber(rs.getString("hsSerial"));
		hs.setAssetNumber(rs.getString("hsAsset"));
		hs.setStatus(rs.getString("hsStatus"));
		
		hs.setModel(m);
		hs.setWarrantyExp(rs.getDate("hsWarranty"));
		hs.setCreatedAt(rs.getTimestamp("hsCreated"));
		hs.setUpdatedAt(rs.getTimestamp("hsUpdated"));
		
		return hs;
	}

}
