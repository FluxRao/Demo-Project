package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopPaginationInput {
	private int shopPageNumber = 0;
	private int shopPageSize = 10;
	private Sort.Direction shopSortDirection = Sort.Direction.DESC;
	private String shopSortBy = "modifiedOn";
}
