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
import com.rampo.entity.ItemUserMap;
import com.rampo.model.CategoryDTO;
import com.rampo.model.ItemDTO;
import com.rampo.model.input.GetAllCategoriesInput;
import com.rampo.model.input.pagination.PaginationWithoutPageInput;
import com.rampo.model.output.CategoryOutput;
import com.rampo.model.output.pagination.CategoryPaginationOutput;
import com.rampo.repository.CategoryRepository;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.util.ObjectMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	public CategoryPaginationOutput getAllCategoryData(GetAllCategoriesInput categoryInput) {

		Sort sort = Sort.by(categoryInput.getSortDirection(), categoryInput.getSortBy());
		Pageable pageable = PageRequest.of(0, 50, sort);

		Page<Category> pages = categoryRepo.findAll(pageable);
		List<CategoryOutput> outputList = new ArrayList<>();
		for (Category category : pages) {
			CategoryOutput co = ObjectMapper.map(category, CategoryOutput.class);
			outputList.add(co);

		}
		CategoryPaginationOutput out = new CategoryPaginationOutput();
		out.setCategoryList(outputList);
		out.setTotalPage(pages.getTotalPages());
		out.setTotalElements(pages.getTotalElements());
		out.setLast(pages.isLast());
		out.setNumber(pages.getNumber());
		out.setSize(pages.getSize());
		out.setNumberOfElements(pages.getNumberOfElements());

		return out;
	}

	public CategoryPaginationOutput getHomePageCategoryData(PaginationWithoutPageInput categoryInput) {

		Sort sort = Sort.by(categoryInput.getSortDirection(), categoryInput.getSortBy());
		Pageable pageable = PageRequest.of(0, 7, sort);

		Page<Category> pages = categoryRepo.findAll(pageable);
		List<CategoryOutput> outputList = new ArrayList<>();
		for (Category category : pages) {
			CategoryOutput co = ObjectMapper.map(category, CategoryOutput.class);
			outputList.add(co);

		}
		CategoryPaginationOutput out = new CategoryPaginationOutput();
		out.setCategoryList(outputList);
		out.setTotalPage(pages.getTotalPages());
		out.setTotalElements(pages.getTotalElements());
		out.setLast(pages.isLast());
		out.setNumber(pages.getNumber());
		out.setSize(pages.getSize());
		out.setNumberOfElements(pages.getNumberOfElements());

		return out;
	}

	public Map<String, Object> findByCategory(String categoryName, String userName, String city) {

		Map<String, Object> output = new HashMap<>();
		output.put("category", ObjectMapper.map(categoryRepo.findById(categoryName).get(), CategoryDTO.class));

		List<Item> items = itemRepo
				.findByCategory_CatNameAndShop_ShopCityIgnoreCaseAndShop_IsActiveTrueAndCategory_IsActiveTrueAndIsActiveTrue(
						categoryName, city);

		List<ItemDTO> itemDtoList = new ArrayList<>();
		for (Item item : items) {
			ItemDTO itemDTO = ObjectMapper.map(item, ItemDTO.class);
			itemDTO.setCategory(item.getCategory().getCatName());
			ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemDTO.getItemId(), userName);
			if (ium != null) {
				itemDTO.setDidLike(ium.isDidLike());
			} else {
				itemDTO.setDidLike(false);
			}

			itemDtoList.add(itemDTO);
		}

		output.put("items", itemDtoList);
		return output;
	}
}
