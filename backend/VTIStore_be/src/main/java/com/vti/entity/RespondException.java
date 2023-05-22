package com.vti.entity;

import lombok.Data;

@Data
public class RespondException extends RuntimeException{
	private String messege;

	public RespondException(String messege) {
		super();
		this.messege = messege;
	}
}
