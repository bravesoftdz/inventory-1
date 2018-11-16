package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.locations.ProductionArea;

@Repository
@Transactional(readOnly = false)
public class LocationDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<ProductionArea> getLocations(){
    	String query = "SELECT * FROM locations";
    	return jdbcTemplate.query(query, new BeanPropertyRowMapper<ProductionArea>(ProductionArea.class));
    }
    
    
    public List<ProductionArea> getAvailableLocations(){
    	String query = "SELECT lo.* " + 
		    		   "  FROM locations lo " + 
		    		   "  LEFT JOIN production_assets pa ON pa.location_id = lo.id  " + 
		    		"  WHERE pa.location_id IS NULL";
    	return jdbcTemplate.query(query, new BeanPropertyRowMapper<ProductionArea>(ProductionArea.class));
    }
	public ProductionArea getLocation(Long id) {
		String query = "call _proc_getStationById(?)";
		try {
			return jdbcTemplate.queryForObject(query, new Object[] {id},new BeanPropertyRowMapper<ProductionArea>(ProductionArea.class));
	
		}catch(EmptyResultDataAccessException e) { return null; } 
	}

	public void addLocation(ProductionArea station) {
		String query = "call _proc_addStation(?,?)";
		
		//jdbcTemplate.query(query, new Object[] {station.getStationId(),station.getStationDesc()},new BeanPropertyRowMapper<Station>(Station.class));
	}
	
	public void deleteLocation(Long id) {
		
		String query = "call _proc_deleteStation(?)";
		
		jdbcTemplate.query(query, new Object[] {id},new BeanPropertyRowMapper<ProductionArea>(ProductionArea.class));
	
	}

	public void editLocation(ProductionArea station) {		
		String query = "call _proc_editStation(?,?,?)";	
		//jdbcTemplate.query(query, new Object[] {station.getId(),station.getStationId(),station.getStationDesc()},new BeanPropertyRowMapper<Station>(Station.class));
	
	}

	public List<ProductionArea> searchStation(String search) {
		String query ="call _proc_searchStations(?);";
		
		return jdbcTemplate.query(query, new Object[] {search},new BeanPropertyRowMapper<ProductionArea>(ProductionArea.class));
	}


    
}
