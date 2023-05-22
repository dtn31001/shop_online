package com.vti.service.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Cart;
import com.vti.entity.Product;
import com.vti.entity.RespondException;
import com.vti.entity.User;
import com.vti.entity.DTO.CartDTO;
import com.vti.entity.Form.CartForm;
import com.vti.repository.Irepository.ICartRespository;
import com.vti.repository.Irepository.IProductRepository;
import com.vti.repository.Irepository.IUserRepository;
import com.vti.service.Iservice.ICartService;
import com.vti.specification.CartSpecification;


@Service
public class CartService implements ICartService{

	@Autowired
	private ICartRespository cartRespository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IUserRepository userRepository;
	@Override
	public Page<Cart> getAllCarts(Pageable pageable, String search) {
		Specification<Cart> where = CartSpecification.buidWhere(search);
		return cartRespository.findAll(where, pageable);
	}
	@Override
	public Cart getCartByID(int id) {
		if (cartRespository.findById(id).isEmpty()) {
			throw new RespondException("Transaction is not found");
		}
		return cartRespository.findById(id).get();
	}
	
	@Override
	public void createTransaction(CartForm form) {
		List<Product> products = new ArrayList<>();
		for (Integer id : form.getProductId()) {
			Product product = productRepository.findById(id).get();
			products.add(product);
		}
		
		User user = userRepository.findById(form.getUserId()).get();
		
		Cart cart = new Cart();
		cart.setAmount(form.getAmount());
		cart.setUser(user);
		cart.setComment(form.getComment());
		cart.setProducts(products);
		cart.setPaid(form.isPaid());
		cartRespository.save(cart);	
	}
	@Override
	public void updateCart(int id, CartForm form) {
		Cart cart = cartRespository.findById(id).get();
		cart.setComment(form.getComment());
		cartRespository.save(cart);
		
	}
	@Override
	public void deleteCart(int id) {
	cartRespository.deleteById(id);
		
	}
	

	

	

}
