package com.rampo.model.input;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoriesInput {
	private Sort.Direction sortDirection = Sort.Direction.DESC;
	private String sortBy;
	private String userName;
}
