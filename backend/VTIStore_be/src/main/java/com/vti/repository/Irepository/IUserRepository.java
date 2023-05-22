package com.vti.repository.Irepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.entity.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	boolean existsByEmail(String email);

	Optional<User> findByUserName(String username);

	boolean existsByUserName(String username);

	
}
