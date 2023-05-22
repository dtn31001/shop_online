package com.vti.service.Iservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Cart;
import com.vti.entity.Form.CartForm;

public interface ICartService {

	Page<Cart> getAllCarts(Pageable pageable, String search);

	Cart getCartByID(int id);

	void createTransaction(CartForm form);

	void updateCart(int id, CartForm form);

	void deleteCart(int id);


}
