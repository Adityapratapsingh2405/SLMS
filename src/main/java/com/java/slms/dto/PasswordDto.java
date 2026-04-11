package com.java.slms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto
{
	private String email;
	private String panNumber;
	private String userType;
	private String password;
	private String oldpassword;
}
