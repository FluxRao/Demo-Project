package com.rampo.model.input.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInput {

	private PaginationInput offerInput;
	private PaginationInput shopInput;
	private PaginationInput categoryInput;
}
