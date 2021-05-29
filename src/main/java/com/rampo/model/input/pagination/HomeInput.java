package com.rampo.model.input.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInput {

	private PaginationWithoutPageInput offerInput;
	private PaginationWithoutPageInput shopInput;
	private PaginationWithoutPageInput categoryInput;
	private String username;
	private String city;
}
