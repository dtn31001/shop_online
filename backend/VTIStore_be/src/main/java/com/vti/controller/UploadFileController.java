package com.vti.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.vti.entity.Product;
import com.vti.service.Iservice.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import com.vti.service.service.AWSS3Service;

@RestController
@RequestMapping(value = "api/file")
@CrossOrigin("*")

public class UploadFileController {

	@Autowired
	UploadFileService uploadFileService;
	@Autowired
	private AWSS3Service cloudinary;




	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_MANAGER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> uploadfile(@RequestParam("file") MultipartFile file) throws IOException {
		String imageURL = cloudinary.uploadFile(file);
//		product.setImg(imageURL);
//		productSE.save
		return new ResponseEntity<>(imageURL, HttpStatus.CREATED);

	}

	@GetMapping("/getImage/{name}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getFile(@PathVariable String name) {
		return uploadFileService.getImage(name);
	}
}
