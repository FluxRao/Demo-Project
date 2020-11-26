package com.rampo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

	private String catName;

	private String categoryDetail;

	private boolean isActive;

	private String imageUrl1;

	private Date createdOn;

	private Date modifiedOn;
}
