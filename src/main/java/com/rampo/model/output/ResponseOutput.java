package com.rampo.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOutput {

	private Object data;
	private String message;
	private boolean success;
	private int statusCode;
}
