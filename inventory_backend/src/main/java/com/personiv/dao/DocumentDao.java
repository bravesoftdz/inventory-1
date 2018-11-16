package com.personiv.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.Document;


@Repository
@Transactional(readOnly = false)
public class DocumentDao extends JdbcDaoSupport {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public void uploadDocument(byte[] content) {
    	String  sql ="INSERT INTO documents(content) VALUES(?)";
    	jdbcTemplate.update(sql,new Object[] {content});
    }

	public Document downloadDocument() {
		String sql ="SELECT * FROM documents where id =1";
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Document>(Document.class));
	}
}
