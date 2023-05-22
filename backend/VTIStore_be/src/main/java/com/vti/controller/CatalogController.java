package com.vti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Catalog;
import com.vti.entity.Form.CatalogForm;
import com.vti.repository.Irepository.ICatalogRepository;
import com.vti.service.Iservice.ICatalogService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {
	@Autowired
	ICatalogRepository catalogRepository;
	
	@Autowired
	private ICatalogService catalogService;

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public List<Catalog> getAllCatalogs() {
		List<Catalog> listCatalog = catalogRepository.findAll();
		return listCatalog;
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public Optional<Catalog> getById(@PathVariable Integer id) {
		return catalogRepository.findById(id);
	}
	
	@GetMapping(value = "/CatalogName/{name}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public Catalog getByName(@PathVariable String name) {
		return catalogRepository.findByName(name);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createCatalog(@RequestBody CatalogForm form){
		catalogService.createCatalog(form);
		return new ResponseEntity<String>("Create uccessfully!", HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateCatalog(@PathVariable int id, @RequestBody CatalogForm form){
		catalogService.updateCatalog(id, form);
		return new ResponseEntity<String>("Update Successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCatalog(@PathVariable int id){
		catalogService.deleteCatalog(id);
		return new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
	}
}
