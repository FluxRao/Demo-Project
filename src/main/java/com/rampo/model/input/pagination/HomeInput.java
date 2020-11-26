package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInput {

	private int offerPageNumber = 0;
	private int offerPageSize = 10;
	private Sort.Direction offerSortDirection = Sort.Direction.DESC;
	private String offerSortBy = "modifiedOn";
	private int shopPageNumber = 0;
	private int shopPageSize = 10;
	private Sort.Direction shopSortDirection = Sort.Direction.DESC;
	private String shopSortBy = "modifiedOn";
	private int categoryPageNumber = 0;
	private int categoryPageSize = 10;
	private Sort.Direction categorySDirection = Sort.Direction.DESC;
	private String categorySortBy = "modifiedOn";
}
