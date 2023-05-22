package com.vti.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "product_id")
	@Id // khoa chinh
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@NotBlank
	@Size(max = 100)
	@Column(name = "product_name")
	private String name;

	@Column(name = "price")
	private float price;

	
	@Column(name = "discount")
	private int discount;

	//@NotBlank
	@Size(max = 500)
	@Column(name = "description")
	private String description;

	//@NotBlank
	@Size(max = 150)
	@Column(name = "img_url")
	private String img;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="catalog_id")
	@JsonIgnore
	private Catalog catalog;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
	@JsonIgnore
	private List<Cart> carts = new ArrayList<>();



}
