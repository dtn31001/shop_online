package com.vti.service.Iservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.User;
import com.vti.entity.Form.UserForm;

public interface IUserService {

	Page<User> getAllUsers(Pageable pageable);

	User getUserByID(int id);

	void updateProduct(int id, UserForm form);

	void deleteProduct(int id);

}
