package com.rampo.model.output.pagination;

import java.util.List;

import com.rampo.model.output.CategoryOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPaginationOutput {

	private List<CategoryOutput> categoryList;
	private int totalPage;
	private long totalElements;
	private boolean last;
	private int number;
	private int size;
	private int numberOfElements;

}
