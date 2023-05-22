package com.vti.entity.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CartDTO {
	private int id;
	private double amount;
	private boolean is_paid;
	private String comment;
	private String userName;
	private List<ProductDTO> products;
	public CartDTO(int id, double amount, boolean is_paid, String comment, String userName, List<ProductDTO> products) {
		super();
		this.id = id;
		this.amount = amount;
		this.is_paid = is_paid;
		this.comment = comment;
		this.userName = userName;
		this.products = products;
	}
	
}
