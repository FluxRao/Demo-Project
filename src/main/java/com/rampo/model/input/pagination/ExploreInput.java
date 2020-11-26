package com.rampo.model.input.pagination;

import lombok.Data;

@Data
public class ExploreInput {

	private OfferPaginationInput HurryUpInput;
	private OfferPaginationInput mostPopular;
	private OfferPaginationInput topRated;
}
