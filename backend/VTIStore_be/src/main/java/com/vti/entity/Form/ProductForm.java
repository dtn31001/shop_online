package com.vti.entity.Form;

import java.io.Serializable;

import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.multipart.MultipartFile;

import com.vti.entity.Catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm implements Serializable{

	private String name;
	private float price;
	private int discount;
	private String catalogName;
	private String description;
	private String img;
	
//	private MultipartFile multipartFile;
	
}
