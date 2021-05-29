package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInput {

	private String itemName;
	private double amount;
	private String detail;
	private String category;
	private String imageUrl1;
	private long shopId;
}
