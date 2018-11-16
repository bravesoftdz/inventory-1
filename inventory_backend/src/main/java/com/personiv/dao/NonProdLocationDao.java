package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.NonProdLocation;

@Repository
@Transactional(readOnly = false)
public class NonProdLocationDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }

    public List<NonProdLocation> getNonProdLocations(){
    	String sql ="SELECT id, loc_name locationName, created_at createdAt,updated_at updatedAt FROM non_prod_loc";
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NonProdLocation>(NonProdLocation.class));
    }
    
    public void addNonProdLocation(NonProdLocation npLoc){
    	String sql ="INSERT INTO non_prod_loc SET loc_name = ? ";
    	 jdbcTemplate.update(sql,new Object[] {
    		npLoc.getLocationName()
    	 });
    }
    
    public void updateNonProdLocation(NonProdLocation npLoc){
    	String sql="UPDATE non_prod_loc SET loc_name =?, updated_at = CURRENT_TIMESTAMP WHERE id =?";
    	 jdbcTemplate.update(sql,new Object[] {
    	    npLoc.getLocationName(),
    	    npLoc.getId()		
    	 });
    }
}
