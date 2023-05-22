package com.vti.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.RespondException;
import com.vti.entity.User;
import com.vti.entity.Form.UserForm;
import com.vti.repository.Irepository.IUserRepository;
import com.vti.service.Iservice.IUserService;
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	@Override
	public User getUserByID(int id) {
		
		if (userRepository.findById(id).isEmpty()) {
			throw new RespondException("User khong ton tai");
		}
		return userRepository.findById(id).get();
	}
	@Override
	public void updateProduct(int id, UserForm form) {
		User user = userRepository.findById(id).get();
		user.setAdress(form.getAdress());
		user.setPhone(form.getPhone());
		
		userRepository.save(user);
		
	}
	@Override
	public void deleteProduct(int id) {
		userRepository.deleteById(id);
		
	}

}
