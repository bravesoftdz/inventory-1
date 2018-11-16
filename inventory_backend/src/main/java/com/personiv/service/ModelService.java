package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.ModelDao;
import com.personiv.model.assets.Model;

@Service
public class ModelService {

	@Autowired
	private ModelDao modelDao;
	
	public List<Model> getModels(){
		return modelDao.getModels();
	}

	public void addModel(Model model) {
		modelDao.addModel(model);
		
	}

	public void updateModel(Model model) {
		modelDao.updateModel(model);
		
	}

	public void deleteModel(Model model) {
		modelDao.deleteModel(model);
		
	}
}
