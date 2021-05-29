package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemid")
	private long itemId;

	@Column(name = "itemname", nullable = false, length = 50)
	private String itemName;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "detail", length = 1000, nullable = true)
	private String detail;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "imageurl1", nullable = true)
	private String imageUrl1;

	@ManyToOne
	@JoinColumn(name = "shopid", nullable = true)
	private Shop shop;

	@ManyToOne
	@JoinColumn(name = "brand", nullable = true)
	private Brand brand;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@Column(name = "rating", nullable = true)
	private float rating;

	@Column(name = "noofratings", nullable = true, columnDefinition = "int default 0")
	private long noOfRatings;

	@Column(name = "views", nullable = false, columnDefinition = "int default 0")
	private long views;

	@Column(name = "likes", nullable = true, columnDefinition = "int default 0")
	private long likes;

	@Column(name = "createdon", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;
}
