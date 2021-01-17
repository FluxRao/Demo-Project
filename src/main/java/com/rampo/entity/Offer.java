package com.rampo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offerid")
	private long offerId;

	@Column(name = "offername", nullable = false, length = 30, unique = false)
	private String offerName;

	@Column(name = "percentacge", nullable = true)
	private int percentage;

	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "startdate", nullable = false)
	private LocalDate startDate;

	@Column(name = "enddate")
	private LocalDate endDate;

	@Column(name = "starttime")
	private LocalTime startTime;

	@Column(name = "endtime")
	private LocalTime endTime;

	@Column(name = "baseparam", nullable = true, length = 50)
	private String baseParam;

//	@ManyToOne
//	@JoinColumn(name = "offertypeid", nullable = false)
//	private OfferType offertype;

	@Column(name = "rating", nullable = true, columnDefinition = "decimal default 0")
	private double rating;

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
	@Column(name = "modifiedon")
	private Date modifiedOn;
}
