package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInput {

	private int pageNumber = 0;
	private int pageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.DESC;
	private String sortBy;
	private String userName;
	private String city;
}
