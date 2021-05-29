package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingInput {

	private long itemId;
	private long shopId;
	private long offerId;
	private String brandName;
	private long rating;
}
