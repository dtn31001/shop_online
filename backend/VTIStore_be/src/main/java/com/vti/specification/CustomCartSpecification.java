package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Cart;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomCartSpecification implements Specification<Cart>{
	private String field;
	private Object value;

	public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (value == null) {
			return null;
		}
		if (field.equalsIgnoreCase("user")) {
			return criteriaBuilder.like(root.get("user"), value.toString());
		}
		return null;
	}

	public CustomCartSpecification(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}
}
