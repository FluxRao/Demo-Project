package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "categoryname")
	private String catName;

	@Column(name = "categorydetail", nullable = true, length = 1000)
	private String categoryDetail;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "imageurl1", nullable = true)
	private String imageUrl1;

	@Column(name = "imageurl2", nullable = true)
	private String imageUrl2;

	@Column(name = "createdon", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;
}
