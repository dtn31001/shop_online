package com.vti.repository.Irepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.ERole;
import com.vti.entity.Role;


public interface IRoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(ERole name);
}
