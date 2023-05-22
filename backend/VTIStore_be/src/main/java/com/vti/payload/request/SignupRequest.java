package com.vti.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class SignupRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

	private Set<String> role;
}
