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

import com.personiv.model.assets.Model;

@Repository
@Transactional(readOnly = false)
public class ModelDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<Model> getModels(){
    	String sql ="SELECT * FROM models ORDER BY id DESC";
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Model>(Model.class));
    }

	public void addModel(Model model) {
		String sql ="INSERT INTO models(name) VALUES(?)";
		jdbcTemplate.update(sql,new Object[] {model.getName()});
		
	}

	public void updateModel(Model model) {
		String sql ="UPDATE models SET name =?, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		jdbcTemplate.update(sql,new Object[] {model.getName(), model.getId()});
		
	}
	
	public void deleteModel(Model model) {
		String sql ="DELETE FROM models where id =?";
		jdbcTemplate.update(sql,new Object[] {model.getId()});
		
	}
}
