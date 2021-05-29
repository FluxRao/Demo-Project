package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerachInput {
	private String userName;
	private String keyword;
	private String city;
}
