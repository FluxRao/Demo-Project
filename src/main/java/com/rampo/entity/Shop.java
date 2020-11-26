package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "shop")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopid")
	private long shopId;

	@Column(name = "shopname", length = 100, nullable = false)
	private String shopName;

	@Column(name = "shopdetail", length = 1000, nullable = true)
	private String shopDetail;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "shopcity", length = 30, nullable = false)
	private String shopCity;

	@Column(name = "shopstate", length = 30, nullable = false)
	private String shopState;

	@Column(name = "shopcountry", length = 30, nullable = false)
	private String shopCountry;

	@Column(name = "pincode", length = 6, nullable = false)
	private String pincode;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "shopowner", length = 50, nullable = false)
	private String shopOwner;

	@Column(name = "shopmanager", length = 50, nullable = false)
	private String shopManager;

	@Column(name = "shopcontactno", length = 10, nullable = false)
	private String contactNo;

	@Column(name = "shopmobileno", length = 10)
	private String shopMobileNo;

	@Column(name = "rating", nullable = true, columnDefinition = "decimal default 0")
	private double rating;

	@Column(name = "noofratings", nullable = true, columnDefinition = "int default 0")
	private long noOfRatings;

	@Column(name = "views", nullable = false, columnDefinition = "int default 0")
	private long views;

	@Column(name = "imageurl1", nullable = true)
	private String imageUrl1;

	@Column(name = "imageurl2", nullable = true)
	private String imageUrl2;

	@Column(name = "imageurl3", nullable = true)
	private String imageUrl3;

	@Column(name = "createdon", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;

}
