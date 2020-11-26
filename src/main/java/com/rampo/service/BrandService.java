package com.rampo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.model.BrandDTO;
import com.rampo.repository.BrandRepository;
import com.rampo.util.ObjectMapper;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepo;

	public List<BrandDTO> getAllBrandData() {

		return ObjectMapper.mapList(brandRepo.findAll(), BrandDTO.class);
	}
}
