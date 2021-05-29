package com.rampo.model.input.pagination;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationWithoutPageInput {

	private Sort.Direction sortDirection = Sort.Direction.DESC;
	private String sortBy;
}
