package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Product;

import lombok.Data;
@Data
public class CustomProductSpecification implements Specification<Product>{
	private String field;
	private Object value;
	
	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (value == null) {
			return null;
		}
		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}
		return null;
	}

	public CustomProductSpecification(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}
	
}
