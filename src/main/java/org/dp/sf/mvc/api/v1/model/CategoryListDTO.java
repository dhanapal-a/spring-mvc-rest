package org.dp.sf.mvc.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryListDTO {
	
	public List<CategoryDTO> categories;

}
