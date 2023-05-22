package com.vti.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.vti.entity.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vti.entity.User;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String phone;
	
	private String adress;
	
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
//	public UserDetailsImpl(int id, String username, String phone, String adress, String email, String password,
//			Collection<? extends GrantedAuthority> authorities) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.phone = phone;
//		this.adress = adress;
//		this.email = email;
//		this.password = password;
//		this.authorities = authorities;
//	}
	

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUserName(), 
				user.getPhone(),
				user.getAdress(),
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

//	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}


//	public int getId() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	public String getEmail() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	public String getPhone() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	public String getAdress() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
