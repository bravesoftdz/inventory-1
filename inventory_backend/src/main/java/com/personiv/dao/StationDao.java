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

import com.personiv.model.locations.Station;

@Repository
@Transactional(readOnly = false)
public class StationDao extends JdbcDaoSupport {
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }

	public void addStation(Station station) {
		String sql ="INSERT INTO locations(name,tower,department) VALUES(?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				station.getStationName(),
				station.getTower(),
				station.getDepartment()
		});
	}
	
	public List<Station> getStations(){
		String sql ="SELECT id,name stationName,department,tower,createdAt,updatedAt FROM locations";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Station>(Station.class));
	}
	
	public void updateStation(Station station) {
		String sql ="UPDATE locations SET name =?, tower =?, department =?, updatedAt =  CURRENT_TIMESTAMP WHERE id =?";
		jdbcTemplate.update(sql, new Object[] {
				station.getStationName(),
				station.getTower(),
				station.getDepartment(),
				station.getId()
		});
	}
	
	

}
