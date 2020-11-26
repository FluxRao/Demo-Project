package com.rampo.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

	private boolean isActive;

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalTime startTime;

	private LocalTime endTime;

	private String baseParam;

//	private OfferType offertype;

	private String imageUrl1;

	private double rating;

	private long noOfRatings;

	private long views;

	private Date createdOn;

	private Date modifiedOn;

}
