package org.dp.sf.mvc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.dp.sf.mvc.api.v1.mapper.CategoryMapper;
import org.dp.sf.mvc.api.v1.model.CategoryDTO;
import org.dp.sf.mvc.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryMapper categoryMapper;
	public CategoryRepository categoryRepository; 
	
	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
		
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
