package com.vti.service.Iservice;

import com.vti.entity.Catalog;
import com.vti.entity.Form.CatalogForm;

public interface ICatalogService{

	void createCatalog(CatalogForm form);

	void deleteCatalog(int id);

	void updateCatalog(int id, CatalogForm form);
	
	Catalog getCatalogByName(String name);

}
