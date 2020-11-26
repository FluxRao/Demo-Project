package com.rampo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private String userName;
	private String contactNo;
	private String email;
	private String password;
	private String imageUrl;
	private Date createdOn;
	private Date modifiedOn;
}
