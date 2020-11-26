package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

	private String userName;
	private String contactNo;
	private String email;
	private String password;
	private String imageUrl;
}
