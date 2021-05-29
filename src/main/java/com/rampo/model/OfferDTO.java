package com.rampo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {

	private long offerId;
	private String offerName;
	private String description;
	private boolean isActive;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String baseParam;
//	private OfferType offertype;
	private String imageUrl1;
	private double rating;
	private long noOfRatings;
	private long views;
	private long likes;
	private Date createdOn;
	private Date modifiedOn;
	private boolean didLike;
}
