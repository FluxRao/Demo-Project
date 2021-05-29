package com.rampo.entity;

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

	@Column(name = "percentage", nullable = true)
	private int percentage;

	@Column(name = "decription", nullable = true, length = 500)
	private String description;

	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@Column(name = "startdate", nullable = true, length = 10)
	private String startDate;

	@Column(name = "enddate", nullable = true, length = 10)
	private String endDate;

	@Column(name = "starttime", nullable = true, length = 10)
	private String startTime;

	@Column(name = "endtime", nullable = true, length = 10)
	private String endTime;

	@Column(name = "baseparam", nullable = true, length = 50)
	private String baseParam;

//	@ManyToOne
//	@JoinColumn(name = "offertypeid", nullable = false)
//	private OfferType offertype;

	@Column(name = "rating", nullable = true)
	private float rating;

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
