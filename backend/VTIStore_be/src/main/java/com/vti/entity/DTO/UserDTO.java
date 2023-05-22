package com.vti.entity.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private int id;
	private String userName;
	private String phone;
	private String email;
	private String adress;
	private List<CartDTO> carts;
	
}
