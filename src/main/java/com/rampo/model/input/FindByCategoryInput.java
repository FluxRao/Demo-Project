package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByCategoryInput {

	private String category;
	private String userName;
	private String city;
}
