package com.vti.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Catalog;
import com.vti.entity.Form.CatalogForm;
import com.vti.repository.Irepository.ICatalogRepository;
import com.vti.service.Iservice.ICatalogService;
@Service
public class CatalogService implements ICatalogService{

	@Autowired
	private ICatalogRepository catalogRepository;
	
	@Override
	public void createCatalog(CatalogForm form) {
		Catalog catalog = new Catalog();
		catalog.setName(form.getName());
		
		catalogRepository.save(catalog);
	}
	
	@Override
	public void deleteCatalog(int id) {
		catalogRepository.deleteById(id);
		
	}
	
	@Override
	public void updateCatalog(int id, CatalogForm form) {
		Catalog catalog = catalogRepository.findById(id).get();
		catalog.setName(form.getName());
		catalogRepository.save(catalog);
	}
	@Override
	public Catalog getCatalogByName(String name) {
			return catalogRepository.findByName(name);
	
	}
	
	
	
}
