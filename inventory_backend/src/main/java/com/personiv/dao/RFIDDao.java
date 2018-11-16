package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.personiv.model.assets.RFID;

@Repository
@Transactional(readOnly = false)
public class RFIDDao  extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<RFID> getRFIDs(){
       	String sql ="SELECT id,badge_number badgeNumber, asset_number assetNumber, created_at createdAt, updated_at updatedAt "+
			    "  FROM rfids ";
	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RFID>(RFID.class));

    }
    
    public RFID getRFIDbyId(Long id){
    	String sql ="SELECT id, badge_number badgeNumber, asset_number assetNumber, created_at createdAt, updated_at updatedAt "+
    			    "  FROM rfids "+
    			    " WHERE id = ?";
    	return jdbcTemplate.queryForObject(sql,new Object[] {id}, new BeanPropertyRowMapper<RFID>(RFID.class));
    }
    
    public RFID getRFIDbyAssetNumber(String assetNumber){
    	String sql ="SELECT id, badge_number badgeNumber, asset_number assetNumber, created_at createdAt, updated_at updatedAt "+
    			    "  FROM rfids "+
    			    " WHERE assetNumber = ?";
    	return jdbcTemplate.queryForObject(sql,new Object[] {assetNumber}, new BeanPropertyRowMapper<RFID>(RFID.class));
    }
    
    
    public void addRFID(RFID rfid){
    	
    	String sql="INSERT INTO rfids(badge_number,asset_number) VALUES(?,?)";
    	
    	jdbcTemplate.update(sql,new Object[] {
    			rfid.getBadgeNumber(),
    			rfid.getAssetNumber()
    	});
    }

	public void updateRFID(RFID rfid) {
		
		String sql="UPDATE rfids SET badge_number =?, asset_number =?, updated_at = CURRENT_TIMESTAMP WHERE id =?";
    	
    	jdbcTemplate.update(sql,new Object[] {
    			rfid.getBadgeNumber(),
    			rfid.getAssetNumber(),
    			rfid.getId()
    	});
		
	}
}
