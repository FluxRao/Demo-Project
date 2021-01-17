package com.rampo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rampo.entity.Category;
import com.rampo.entity.Item;
import com.rampo.model.CategoryDTO;
import com.rampo.model.ItemDTO;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.repository.CategoryRepository;
import com.rampo.repository.ItemRepository;
import com.rampo.util.ObjectMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ItemRepository itemRepo;

	public List<CategoryDTO> getAllCategoryData(PaginationInput categoryInput, String userName) {

		Sort sort = Sort.by(categoryInput.getSortDirection(), categoryInput.getSortBy());
		Pageable pageable = PageRequest.of(categoryInput.getPageNumber(), categoryInput.getPageSize(), sort);

		Page<Category> categoryPage = categoryRepo.findAll(pageable);
		List<CategoryDTO> outputList = new ArrayList<>();
		for (Category category : categoryPage) {
			outputList.add(ObjectMapper.map(category, CategoryDTO.class));
		}
		return outputList;
	}

	public Map<String, Object> findByCategory(String categoryName, String userName) {

		Map<String, Object> output = new HashMap<>();
		output.put("category", ObjectMapper.map(categoryRepo.findById(categoryName).get(), CategoryDTO.class));

		List<Item> itemList = itemRepo.findByCategory_catName(categoryName);
		List<ItemDTO> itemDtoList = ObjectMapper.mapList(itemList, ItemDTO.class);
		output.put("items", itemDtoList);
		return output;
	}

}
