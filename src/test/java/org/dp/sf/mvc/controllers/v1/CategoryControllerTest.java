package org.dp.sf.mvc.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.dp.sf.mvc.api.v1.model.CategoryDTO;
import org.dp.sf.mvc.controllers.RestResponseEntityExceptionHandler;
import org.dp.sf.mvc.services.CategoryService;
import org.dp.sf.mvc.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CategoryControllerTest {

	private static final String NAME = "joe";

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	void testGetallCatetories() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1l);
		category1.setName(NAME);

		CategoryDTO category2 = new CategoryDTO();
		category2.setId(2l);
		category2.setName("Bob");

		List<CategoryDTO> categories = Arrays.asList(category1, category2);

		// then
		when(categoryService.getAllCategories()).thenReturn(categories);

		mockMvc.perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.categories", hasSize(2)));

	}

	@Test
	void testGetCategoryByName() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1l);
		category1.setName(NAME);

		when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

		mockMvc.perform(get("/api/v1/categories/Jim").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	 @Test
	    public void testGetByNameNotFound() throws Exception {

	        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

	        mockMvc.perform(get("/api/v1/customers//Foo")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound());
	    }

}
