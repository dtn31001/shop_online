package com.vti.controller;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vti.entity.Product;
import com.vti.entity.DTO.ProductDTO;
import com.vti.entity.Form.ProductForm;
import com.vti.service.Iservice.IProductService;

@RestController
@RequestMapping(value = "api/products")
@CrossOrigin("*")

public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') or isAnonymous()")
	public Page<ProductDTO> getAllProducts(Pageable pageable,  @RequestParam(required = false) String search) {

		Page<Product> product = productService.getAllProducts(pageable, search);

		List<Product> listProducts = product.getContent();
// convert entities --> dtos
		List<ProductDTO> dtos = modelMapper.map(listProducts, new TypeToken<List<ProductDTO>>() {
		}.getType());

		Page<ProductDTO> pageDTO = new PageImpl<>(dtos, pageable, product.getTotalElements());

		return pageDTO;

	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') or isAnonymous()")
	public ProductDTO getProductByID(@PathVariable int id) {
		Product entityProduct = productService.getProductByID(id);
		// convert entity --> dto
		ProductDTO dto = modelMapper.map(entityProduct, ProductDTO.class);
		return dto;
	}
	
//	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestParam("file") MultipartFile file,
			@RequestParam String name,
			@RequestParam float price,
			@RequestParam String catalogName,
			@RequestParam String description,
			@RequestParam int discount
			) throws IOException {
		productService.createProduct(name, price, catalogName, description, discount, file);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}


	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProduct(
			@PathVariable int id, 
			@RequestParam("file") MultipartFile file,
			
			@RequestParam float price, 
			@RequestParam String description,
			@RequestParam int discount) throws IOException {
		productService.updateProduct(id, price, description, discount, file );
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
	

}
