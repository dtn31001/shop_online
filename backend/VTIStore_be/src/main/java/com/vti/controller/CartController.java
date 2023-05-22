package com.vti.controller;

import java.util.List;

import javax.transaction.Transaction;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Cart;
import com.vti.entity.User;
import com.vti.entity.DTO.CartDTO;
import com.vti.entity.Form.CartForm;
import com.vti.repository.Irepository.ICartRespository;
import com.vti.repository.Irepository.IUserRepository;
import com.vti.service.Iservice.ICartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/carts")
public class CartController {
	@Autowired
	private ICartService cartService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	double amountCheck = 0;

	@Autowired
	private ICartRespository cartRepository;

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public Page<CartDTO> getAllCarts(Pageable pageable, @RequestParam(required = false) String search) {

		Page<Cart> cart = cartService.getAllCarts(pageable, search);

		List<Cart> listCarts = cart.getContent();
// convert entities --> dtos
		List<CartDTO> dtos = modelMapper.map(listCarts, new TypeToken<List<CartDTO>>() {
		}.getType());

		Page<CartDTO> pageDTO = new PageImpl<>(dtos, pageable, cart.getTotalElements());

		return pageDTO;

	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public CartDTO getCartByID(@PathVariable int id) {
		Cart entityCart = cartService.getCartByID(id);
		// convert entity --> dto
		CartDTO dto = modelMapper.map(entityCart, CartDTO.class);
		return dto;
	}
	@PostMapping()
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createCart(@RequestBody CartForm form) {
		cartService.createTransaction(form);

		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateCart(@PathVariable int id, @RequestBody CartForm form) {
		cartService.updateCart(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCart(@PathVariable int id) {
		cartService.deleteCart(id);
		return new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
	}

	@PostMapping("/payment/{sumAmount}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> payAll(@PathVariable("sumAmount") double sumAmount) {
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
				return null;
			}
			UserDetails user;
			Object princical = authentication.getPrincipal();
			if (princical instanceof UserDetails) {
				user = ((UserDetails) princical);
			} else {
				user = null;
			}
			if (user == null) {
				return new ResponseEntity<>("Thanh toán lỗi xin vui lòng thử lại", HttpStatus.EXPECTATION_FAILED);
			}
			User userLogin = userRepository.findByUserName(user.getUsername()).get();
			return new ResponseEntity<>("Thanh toán thành công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

		}
	}
}
