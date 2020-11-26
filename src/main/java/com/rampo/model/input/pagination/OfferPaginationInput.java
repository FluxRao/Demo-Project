package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferPaginationInput {
	private int offerPageNumber = 0;
	private int offerPageSize = 10;
	private Sort.Direction offerSortDirection = Sort.Direction.DESC;
	private String offerSortBy = "modifiedOn";
}
