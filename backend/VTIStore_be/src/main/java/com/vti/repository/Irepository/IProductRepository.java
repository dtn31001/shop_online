package com.vti.repository.Irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product> {

	Product findByName(String name);

	

}
