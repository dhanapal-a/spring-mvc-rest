package org.dp.sf.mvc.services;

import java.util.List;

import org.dp.sf.mvc.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByName(String name);
}
