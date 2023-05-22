package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Product;

public class ProductSpecification {
	@SuppressWarnings("deprecation")

	public static Specification<Product> buidWhere(String search) {
		Specification<Product> where = null;
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomProductSpecification name = new CustomProductSpecification("name", search);
			where = Specification.where(name);
		}
		return where;
}
}
