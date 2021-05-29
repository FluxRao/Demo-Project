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
@Table(name = "brand")
public class Brand {

	@Id
	@Column(name = "brandname", length = 30, nullable = false)
	private String brandName;

	@Column(name = "branddetail", length = 1000, nullable = true)
	private String brandDetail;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "rating", nullable = true, columnDefinition = "int default 0")
	private long rating;

	@Column(name = "noofratings", nullable = true, columnDefinition = "int default 0")
	private long noOfRatings;

	@Column(name = "views", nullable = false, columnDefinition = "int default 0")
	private long views;

	@Column(name = "likes", nullable = true, columnDefinition = "int default 0")
	private long likes;

	@Column(name = "imageurl1", nullable = true)
	private String imageUrl1;

	@Column(name = "createdon", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;

}
