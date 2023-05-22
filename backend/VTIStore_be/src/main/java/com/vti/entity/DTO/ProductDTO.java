package com.vti.entity.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ProductDTO {
	private int id;
	private String name;
	private float price;
	private int discount;
	private String catalogName;
	private int catalogId;
	private String description;
	private String img;
	
	
	
	public ProductDTO(String name, float price, int discount, String description, String img, String catalogName, int catalogId) {
		super();
		this.name = name;
		this.price = price;
		this.discount = discount;
		
		this.description = description;
		this.img = img;
		this.catalogName = catalogName;
		this.catalogId = catalogId;
	}
	
	
}
