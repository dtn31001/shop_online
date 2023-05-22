package com.vti.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vti.entity.Product;
import com.vti.entity.User;
import com.vti.entity.DTO.ProductDTO;
import com.vti.entity.DTO.UserDTO;
import com.vti.entity.Form.UserForm;
import com.vti.service.Iservice.IUserService;



@RestController
@RequestMapping(value = "api/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public Page<UserDTO> getAllProducts(Pageable pageable) {

		Page<User> user =userService.getAllUsers(pageable);

		List<User> listUsers = user.getContent();
// convert entities --> dtos
		List<UserDTO> dtos = modelMapper.map(listUsers, new TypeToken<List<UserDTO>>() {
		}.getType());

		Page<UserDTO> pageDTO = new PageImpl<>(dtos, pageable, user.getTotalElements());

		return pageDTO;

	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
	public UserDTO getUserByID(@PathVariable int id) {
		User entityUser = userService.getUserByID(id);
		// convert entity --> dto
		UserDTO dto = modelMapper.map(entityUser, UserDTO.class);
		return dto;
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody UserForm form ) {
		userService.updateProduct(id,form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		userService.deleteProduct(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
}
