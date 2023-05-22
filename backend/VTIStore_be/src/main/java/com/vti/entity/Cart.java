package com.vti.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Table(name = "carts")

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cart_id")
	@Id // khoa chinh
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "is_paid")
	private boolean isPaid;
	
	@Column(name = "amount")
//	@NotBlank
	private double amount;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="cart_product", joinColumns = @JoinColumn(name="cart_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
	@JsonIgnore
	private List<Product> products=new ArrayList<>();

	public void setAmount(Object amount2) {
		// TODO Auto-generated method stub
		
	}

	public void setProducts(List<Product> products2) {
		// TODO Auto-generated method stub
		
	}

	public void setComment(Object comment2) {
		// TODO Auto-generated method stub
		
	}

	public void setPaid(Object paid) {
		// TODO Auto-generated method stub
		
	}

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		
	}

	
}
