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

import com.personiv.model.locations.TrainingRoom;

@Repository
@Transactional(readOnly = false)
public class TrainingRoomDao  extends JdbcDaoSupport {
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<TrainingRoom> getTrainingRooms(){
    	String sql ="SELECT id, name, createdAt, updatedAt FROM training_rooms";
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TrainingRoom>(TrainingRoom.class));
    }
    
    public void addTrainingRoom(TrainingRoom room) {
    	
    	String sql ="INSERT INTO training_rooms(name) VALUES(?)";
    	jdbcTemplate.update(sql, new Object[] {room.getName()});
    }

    public void updateTrainingRoom(TrainingRoom room) {
    	
    	String sql ="UPDATE training_rooms SET name =? WHERE id=?";
    	
    	jdbcTemplate.update(sql, new Object[] {
    			room.getName(), 
    			room.getId()
    	});
    }
}
