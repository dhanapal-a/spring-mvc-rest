package org.dp.sf.mvc.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.dp.sf.mvc.api.v1.mapper.CategoryMapper;
import org.dp.sf.mvc.api.v1.model.CategoryDTO;
import org.dp.sf.mvc.domain.Category;
import org.dp.sf.mvc.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryServiceTest {

	CategoryService categoryService;

	@Mock
	CategoryRepository categoryRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
	}

	@Test
	void testGetAllCategories() {
		
		//given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        
        //then
        when(categoryRepository.findAll()).thenReturn(categories);
        
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
        
        assertEquals(3, categoryDTOList.size());

	}

	@Test
	void testGetCategoryByName() {

		//given
		Category category =new Category();
		category.setId(1L);
		String name = "joe";
		category.setName(name);
		//then
		
		when(categoryRepository.findByName(name)).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
		assertEquals(1, categoryDTO.getId());
		assertEquals(name, categoryDTO.getName());
 	}

}
