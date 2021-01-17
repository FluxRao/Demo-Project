package com.rampo.model.input.pagination;

import lombok.Data;

@Data
public class ExploreInput {

	private PaginationInput HurryUpInput;
	private PaginationInput mostPopular;
	private PaginationInput topRated;
}
