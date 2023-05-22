package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Cart;


public class CartSpecification{
	@SuppressWarnings({ "deprecation" })

	public static Specification<Cart> buidWhere(String search) {
		Specification<Cart> where = null;
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomCartSpecification userName = new CustomCartSpecification("user", where) ;
			where= Specification.where(userName);
		}
		return where;
	}
}
