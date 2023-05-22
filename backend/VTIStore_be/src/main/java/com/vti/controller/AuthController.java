package com.vti.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.ERole;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.payload.reponse.JwtResponse;
import com.vti.payload.reponse.MessageResponse;
import com.vti.payload.request.LoginRequest;
import com.vti.payload.request.SignupRequest;
import com.vti.repository.Irepository.IRoleRepository;
import com.vti.repository.Irepository.IUserRepository;
import com.vti.security.jwt.JwtUtils;
import com.vti.security.service.UserDetailsImpl;
import com.vti.ultils.Constants;

@CrossOrigin(origins = "*") // , maxAge = 3600
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());

		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												userDetails.getId(), 
												userDetails.getUsername(),
												userDetails.getEmail(), 
												userDetails.getPhone(), 
												userDetails.getAdress(), 
												roles));
									}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity.ok(("USERNAME"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.ok(("EMAIL"));
		}

		// Create new user's account
		User user = new User();
//		signUpRequest.getUsername(), signUpRequest.getEmail(),
//				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(),signUpRequest.getAdress(),100000

		System.out.println(signUpRequest.getUsername());
		user.setUserName(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setWalletCast(100000);
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException(Constants.ERROR_ROLE_NOT_FOUND));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case Constants.ROLE_ADMIN -> {
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException(Constants.ERROR_ROLE_NOT_FOUND));
						roles.add(adminRole);
					}
					case Constants.ROLE_MANAGER -> {
						Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
								.orElseThrow(() -> new RuntimeException(Constants.ERROR_ROLE_NOT_FOUND));
						roles.add(modRole);
					}
					default -> {
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException(Constants.ERROR_ROLE_NOT_FOUND));
						roles.add(userRole);
					}
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(Constants.USER_REGISTERED_SUCCESSFUL));
	}
}
