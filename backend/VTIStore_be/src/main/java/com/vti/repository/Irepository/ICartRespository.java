package com.vti.repository.Irepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vti.entity.Cart;
import com.vti.entity.DTO.CartDTO;

@Repository
public interface ICartRespository extends JpaRepository<Cart, Integer>,JpaSpecificationExecutor<Cart>{

	


	
}
