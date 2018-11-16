package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.assets.Monitor;
import com.personiv.rowmapper.MonitorRowMapper;

@Repository
@Transactional(readOnly = false)
public class MonitorDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
//    public List<Monitor> getMonitors(){
//    	String sql ="SELECT mo.id moId, mo.description moDesc, mo.serial_number moSerial,mo.asset_number moAsset, mo.warranty_exp moWarranty, mo.createdAt moCreated,mo.updatedAt moUpdated,  " + 
//    			"           m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated, " + 
//    			"  			IF(pa.monitor_id1 IS NOT NULL OR pa2.monitor_id2 IS NOT NULL,'PROD USE', " + 
//    			"  			IF(ta.monitor_id1 IS NOT NULL OR ta2.monitor_id2 IS NOT NULL,'TRAINING USE', " + 
//    			"  			IF(ir.monitor_id1 IS NOT NULL OR ir2.monitor_id2 IS NOT NULL,'ITD USE','IN STOCK'))) moStatus " + 
//    			"      FROM monitors mo  " + 
//    			"      JOIN models m on mo.model_id = m.id " + 
//    			"      LEFT JOIN prod_assets pa ON mo.id = pa.monitor_id1 " + 
//    			"      LEFT JOIN prod_assets pa2 ON mo.id = pa2.monitor_id2 " + 
//    			"      LEFT JOIN training_assets ta ON mo.id = ta.monitor_id1 " + 
//    			"      LEFT JOIN training_assets ta2 ON mo.id = ta2.monitor_id2 " + 
//    			"      LEFT JOIN it_room_assets ir ON mo.id = ir.monitor_id1 " + 
//    			"      LEFT JOIN it_room_assets ir2 ON mo.id = ir2.monitor_id2 ";
//    	return jdbcTemplate.query(sql, new MonitorRowMapper());
//    }
    
    public List<Monitor> getMonitors(){
    	String sql ="SELECT mo.id moId, mo.description moDesc, mo.serial_number moSerial,mo.asset_number moAsset, mo.warranty_exp moWarranty, mo.createdAt moCreated,mo.updatedAt moUpdated,  " + 
    			"           m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated,'N/A' moStatus " + 
    			"      FROM monitors mo  "+
    			" 	   JOIN models m on mo.model_id = m.id ";
    	return jdbcTemplate.query(sql, new MonitorRowMapper());
    }
    
    
    public List<Monitor> getAvailableMonitors(){
    	String sql ="SELECT mo.id moId, mo.description moDesc, mo.serial_number moSerial,mo.asset_number moAsset, mo.warranty_exp moWarranty, mo.createdAt moCreated,mo.updatedAt moUpdated,  " + 
    			"           m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated," + 
    			"  	        IF(pa.monitor_id1 IS NOT NULL OR pa2.monitor_id2 IS NOT NULL,'PROD USE'," + 
    			"  	        IF(ta.monitor_id1 IS NOT NULL OR ta2.monitor_id2 IS NOT NULL,'TRAINING USE'," + 
    			"  			IF(ir.monitor_id1 IS NOT NULL OR ir2.monitor_id2 IS NOT NULL,'ITD USE','IN STOCK'))) moStatus" + 
    			" 	   FROM monitors mo  " + 
    			" 	   JOIN models m on mo.model_id = m.id " + 
    			" 	   LEFT JOIN production_assets pa ON mo.id = pa.monitor_id1 " + 
    			" 	   LEFT JOIN production_assets pa2 ON mo.id = pa2.monitor_id2 " + 
    			" 	   LEFT JOIN training_assets ta ON mo.id = ta.monitor_id1 " + 
    			" 	   LEFT JOIN training_assets ta2 ON mo.id = ta2.monitor_id2 " + 
    			" 	   LEFT JOIN it_room_assets ir ON mo.id = ir.monitor_id1 " + 
    			" 	   LEFT JOIN it_room_assets ir2 ON mo.id = ir2.monitor_id2  " + 
    			"     WHERE (pa.monitor_id1 IS NULL AND pa2.monitor_id2 IS NULL) AND " + 
    			"           (ta.monitor_id1 IS NULL AND ta2.monitor_id2 IS NULL) AND " + 
    			"           (ir.monitor_id1 IS NULL AND ir2.monitor_id2 IS NULL) ";
    	return jdbcTemplate.query(sql, new MonitorRowMapper());
    }

	public void updateMonitor(Monitor monitor) {
		String sql ="UPDATE monitors SET serial_number =?, asset_number = ?, description =?, model_id =?, warranty_exp =?, updatedAt = CURRENT_TIMESTAMP WHERE id=?" ;
		jdbcTemplate.update(sql,new Object[] {
				monitor.getSerialNumber(),
				monitor.getAssetNumber(),
				monitor.getDescription(),
				monitor.getModel().getId(),
				monitor.getWarrantyExp(),
				monitor.getId()});
		
	}
	
	public void addMonitor(Monitor monitor) {
		
		String sql ="INSERT INTO monitors(serial_number,asset_number,description,model_id) VALUES(?,?,?,?)";
		
		jdbcTemplate.update(sql,new Object[] {
				monitor.getSerialNumber(),
				monitor.getAssetNumber(),
				monitor.getDescription(),
				monitor.getModel().getId()});
		
	}
    
}
