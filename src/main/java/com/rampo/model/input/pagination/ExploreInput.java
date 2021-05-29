package com.rampo.model.input.pagination;

import lombok.Data;

@Data
public class ExploreInput {

	private PaginationWithoutPageInput hurryUp;
	private PaginationWithoutPageInput mostPopular;
	private PaginationWithoutPageInput topRated;
	private String userName;
	private String city;
}
