package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.assets.Headset;
import com.personiv.rowmapper.HeadsetRowMapper;


@Repository
@Transactional(readOnly = false)
public class HeadsetDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
//    public List<Headset> getHeadsets(){
//    	String sql ="SELECT hs.id  hsId, hs.description  hsDesc, hs.serial_number  hsSerial,hs.asset_number  hsAsset, hs.warranty_exp  hsWarranty, hs.createdAt  hsCreated,hs.updatedAt hsUpdated," + 
//    			"   	    m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated," + 
//    			"		 	IF(pa.headset_id IS NOT NULL,'PROD USE'," + 
//    			"		 	IF(ta.headset_id IS NOT NULL,'TRAINING USE'," + 
//    			"		 	IF(ita.headset_id IS NOT NULL,'ITD USE','IN STOCK'))) hsStatus" + 
//    			"  	   FROM headsets  hs " + 
//    			"  	   JOIN models m on hs.model_id = m.id" + 
//    			"  	   LEFT JOIN prod_assets pa ON pa.headset_id = hs.id " + 
//    			"  	   LEFT JOIN training_assets ta ON ta.headset_id = hs.id " + 
//    			"  	   LEFT JOIN it_room_assets ita on ita.headset_id = hs.id " + 
//    			" ORDER BY hs.id DESC";
//    	return jdbcTemplate.query(sql, new HeadsetRowMapper());
//    }
    
    public List<Headset> getHeadsets(){
    	String sql ="SELECT hs.id  hsId, hs.description  hsDesc, hs.serial_number  hsSerial,hs.asset_number  hsAsset, hs.warranty_exp  hsWarranty, hs.createdAt  hsCreated,hs.updatedAt hsUpdated," + 
    			"   	    m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated, 'N/A' hsStatus " + 
    			"  	   FROM headsets  hs " + 
    			"  	   JOIN models m on hs.model_id = m.id" + 
    			" ORDER BY hs.id DESC";
    	return jdbcTemplate.query(sql, new HeadsetRowMapper());
    }

	public Headset getHeadset(Long id) {
    	String sql ="SELECT hs.id  hsId, hs.description  hsDesc, hs.serial_number  hsSerial,hs.asset_number  hsAsset, hs.warranty_exp  hsWarranty,hs.status  hsStatus, hs.createdAt  hsCreated,hs.updatedAt hsUpdated,"
	  			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated "
	  			  + "  FROM headsets  hs"
	  			  + "  JOIN models m on hs.model_id = m.id"
	  			  + " WHERE hs.id = ?";
    	return jdbcTemplate.queryForObject(sql,new Object[] {id}, new HeadsetRowMapper());
	}

	public List<Headset> getAvailableHeadsets() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateHeadset(Headset headset) {
		String sql = "UPDATE headsets SET serial_number=?, asset_number=?, model_id=?, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		
		jdbcTemplate.update(sql, new Object[] {
				headset.getSerialNumber(),
				headset.getAssetNumber(),
				headset.getModel().getId(),
				headset.getId()
		});
		
	}

	public void addHeadset(Headset headset) {
		
		String sql ="INSERT INTO headsets(serial_number,asset_number,model_id) VALUES(?,?,?)";
		
		jdbcTemplate.update(sql, new Object[]{
				headset.getSerialNumber(),
				headset.getAssetNumber(),
				headset.getModel().getId()
		});
		
	}

	public void deleteHeadset(Headset headset) {
		String sql ="DELETE FROM headsets WHERE id=?";
		jdbcTemplate.update(sql,new Object[] {headset.getId()});
		
	}
}
