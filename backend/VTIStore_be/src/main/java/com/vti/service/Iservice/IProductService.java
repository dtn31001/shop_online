package com.vti.service.Iservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.vti.entity.Product;
import com.vti.entity.Form.ProductForm;

import java.io.IOException;

public interface IProductService {

	Page<Product> getAllProducts(Pageable pageable, String search);

	Product getProductByID(int id);

//	void createProduct(ProductForm form);

//	void updateProduct(int id, ProductForm form);

	void deleteProduct(int id);
	
	Product getProductByName(String name);

	void createProduct(String name, float price, String catalogName, String description, int discount,
			MultipartFile file) throws IOException;

	void updateProduct(int id, float price, String description, int discount, MultipartFile file) throws IOException;



}
