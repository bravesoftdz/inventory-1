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

import com.personiv.model.Supplier;

@Repository
@Transactional(readOnly = false)
public class SupplierDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<Supplier> getSuppliers(){
    	String query = "call _proc_getSuppliers()";
    	List<Supplier> suppliers = jdbcTemplate.query(query, new BeanPropertyRowMapper<Supplier>(Supplier.class));
    	return suppliers;
    }
    
    public Supplier getSupplier(Long id) {
    	String query = "call _proc_getSupplierById(?)";
    	Supplier supplier = null;
    	
    	try {
    		supplier = jdbcTemplate.queryForObject(query,new Object[] {id}, new BeanPropertyRowMapper<Supplier>(Supplier.class));
    	}catch(EmptyResultDataAccessException e) {}
    	
    	return supplier;
    }
}
