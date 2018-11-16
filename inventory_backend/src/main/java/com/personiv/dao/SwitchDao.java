package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.Switch;
import com.personiv.rowmapper.SwitchRowMapper;

@Repository
@Transactional(readOnly = false)
public class SwitchDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    
    public List<Switch> getSwitches(){
    	String sql ="SELECT s.id,s.name,asset_number assetNumber, s.serial_number serialNumber, s.ip_address ipAddress, s.mac_address macAddress, s.createdAt sCreated, s.updatedAt sUpdated,"
    			  + "       m.id mId, m.name mName, m.createdAt mCreated, m.updatedAt mUpdated"
    			  + "  FROM switches s"
    			  + "  JOIN models m on s.model_id = m.id"
    			  + " ORDER by s.id DESC";
    	
    	return jdbcTemplate.query(sql,new SwitchRowMapper());
    }
    
    public Switch getSwitch(Long id){
    	String sql ="SELECT s.id,s.name,asset_number assetNumber, s.serial_number serialNumber,s.ip_address ipAddress,s.mac_address macAddress, s.createdAt sCreated, s.updatedAt sUpdated, "
    			  + "       m.id mId, m.name mName, m.createdAt mCreated, m.updatedAt mUpdated "
    			  + "  FROM switches s"
    			  + "  JOIN models m on s.model_id = m.id"
    			  + " WHERE s.id =?";
    	
    	return jdbcTemplate.queryForObject(sql,new Object[] {id},new SwitchRowMapper());
    }

    
    public void addSwitch(Switch sw){
    	String sql ="INSERT INTO switches(name,asset_number,serial_number,model_id,mac_address,ip_address) VALUES(?,?,?,?,?,?)";
    	
    	jdbcTemplate.update(sql,
    			
    			new Object[] {
    			sw.getName(),
    			sw.getAssetNumber(),
    			sw.getSerialNumber(),
    			sw.getModel().getId(),
    			sw.getMacAddress(),
    			sw.getIpAddress()
    					
    	});
    }
    
    public void editSwitch(Switch sw){
    	String sql ="UPDATE switches SET name =?, asset_number =? ,serial_number =?, model_id=?, mac_address=?, ip_address =?, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
    	
    	jdbcTemplate.update(sql,
    			
    			new Object[] {
    			sw.getName(),
    			sw.getAssetNumber(),
    			sw.getSerialNumber(),
    			sw.getModel().getId(),
    			sw.getMacAddress(),
    			sw.getIpAddress(),
    			sw.getId()
    					
    	});
    	
    }
    
    public void deleteSwitch(Long id) {
    	String sql ="DELETE FROM switches WHERE id =?";
    	jdbcTemplate.update(sql,new Object[] {id});
    }
    
}
