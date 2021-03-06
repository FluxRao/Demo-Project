package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "username", length = 50)
	private String userName;

	@Column(name = "contactno", length = 10, nullable = false, unique = true)
	private String contactNo;

	@Column(name = "email", length = 30, nullable = false, unique = true)
	private String email;

	@Column(name = "password", length = 20, nullable = false)
	private String password;

	@Column(name = "city", length = 50, nullable = true)
	private String city;

	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "imageurl", nullable = true)
	private String imageUrl;

	@Column(name = "createdon", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;
}
