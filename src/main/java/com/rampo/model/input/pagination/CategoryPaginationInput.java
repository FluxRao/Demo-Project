package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPaginationInput {
	private int categoryPageNumber = 0;
	private int categoryPageSize = 10;
	private Sort.Direction categorySDirection = Sort.Direction.DESC;
	private String categorySortBy = "modifiedOn";
}
