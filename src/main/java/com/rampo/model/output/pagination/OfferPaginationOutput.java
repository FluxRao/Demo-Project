package com.rampo.model.output.pagination;

import java.util.Set;

import com.rampo.model.OfferDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferPaginationOutput {

	public Set<OfferDTO> offerList;
	private int totalPage;
	private long totalElements;
	private boolean last;
	private int number;
	private int size;
	private int numberOfElements;
}
