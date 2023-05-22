package com.vti.payload.reponse;

import java.util.List;

import lombok.Data;
@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String email;
	private String phone;
	private String adress;
	private List<String> roles;
	public JwtResponse(String token, int id, String username, String email, String phone, String adress,
			List<String> roles) {
		super();
		this.token = token;
//		this.type = type;
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.roles = roles;
	}

	
}
