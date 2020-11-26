package com.rampo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "userrolesmaster")
public class Roles {

	@Id
	@Column(name = "role", length = 20, unique = true)
	private String role;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

}
