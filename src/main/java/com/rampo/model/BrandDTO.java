package com.rampo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

	private String brandName;
	private String brandDetail;
	private boolean isActive;
	private double rating;
	private long noOfRatings;
	private long views;
	private String imageUrl1;
	private Date createdOn;
	private Date modifiedOn;
}
