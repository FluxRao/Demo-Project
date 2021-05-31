package com.rampo.model.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferSaveInput {

	private List<Long> itemsIds;
	private String offerName;
	private int percentage;
	private String description;
	private String startingDate;
	private String startingTime;
	private String endingDate;
	private String endingTime;
	private String userName;
}
