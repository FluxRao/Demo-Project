package com.rampo.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOutput {

	private String catName;

	private String categoryDetail;

	private boolean isActive;

	private String imageUrl1;

	private String imageUrl2;
}
