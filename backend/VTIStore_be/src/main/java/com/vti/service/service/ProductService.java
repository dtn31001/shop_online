package com.vti.service.service;

import com.vti.service.Iservice.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vti.entity.Catalog;
import com.vti.entity.Product;
import com.vti.entity.RespondException;
import com.vti.repository.Irepository.ICatalogRepository;
import com.vti.repository.Irepository.IProductRepository;
import com.vti.service.Iservice.IProductService;
import com.vti.specification.ProductSpecification;

import java.io.IOException;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	ICatalogRepository catalogRepository;
	@Autowired
	AWSS3Service cloudinary;

	@Autowired
	FileService fileService;
	@Override
	public Page<Product> getAllProducts(Pageable pageable, String search) {
		Specification<Product> where = ProductSpecification.buidWhere(search);
		return productRepository.findAll(where, pageable);
	}

	@Override
	public Product getProductByID(int id) {
		if (productRepository.findById(id).isEmpty()) {
			throw new RespondException("Product khong ton tai");
		}
		return productRepository.findById(id).get();
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);

	}

	@Override
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Autowired
	UploadFileService uploadFileService;

	@Override
	public void createProduct(String name, float price, String catalogName, String description, int discount,
			MultipartFile file) throws IOException {
		Catalog catalog = catalogRepository.findByName(catalogName);

		String urlString = cloudinary.uploadFile(file);
		Product product = new Product();
		product.setName(name);  
		product.setPrice(price);
		product.setDiscount(discount);
		product.setDescription(description);
		product.setImg(urlString);
		product.setCatalog(catalog);
		productRepository.save(product);
		uploadFileService.uploadImageFromProduct(file);
	}

	@Override
	public void updateProduct(int id, float price, String description, int discount, MultipartFile file) throws IOException {
		Product product = productRepository.findById(id).get();
		String urlStringUp = cloudinary.uploadFile(file);

		product.setPrice(price);
		product.setDescription(description);
		product.setDiscount(discount);
		product.setImg(urlStringUp);
		productRepository.save(product);

	}

}
