package com.rampo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

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

	@ManyToOne
	@JoinColumn(name = "brand", nullable = true)
	private Brand brand;

	@ManyToMany
	@JoinTable(name = "itemcategorymap", joinColumns = @JoinColumn(name = "itemid"), inverseJoinColumns = @JoinColumn(name = "category"))
	@JoinColumn(name = "category", nullable = false)
	private List<Category> category;

	@Column(name = "rating", nullable = true, columnDefinition = "decimal default 0")
	private double rating;

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
