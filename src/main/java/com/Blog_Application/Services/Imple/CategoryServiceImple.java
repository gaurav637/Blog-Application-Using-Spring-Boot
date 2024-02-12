package com.Blog_Application.Services.Imple;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog_Application.BlogServices.categoryService;
import com.Blog_Application.Entities.Category;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.CategoryDto;
import com.Blog_Application.Repository.categoryRepo;


@Service
public class CategoryServiceImple implements categoryService {
	
	@Autowired
	private categoryRepo cate;
	
	@Autowired
	private ModelMapper model;

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category category = DtoTOCategory(categorydto);
		Category savedCategory = cate.save(category);
		return this.categoryTODto(savedCategory);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		Category category = cate.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category category1= cate.save(category);
		CategoryDto updateCategory = categoryTODto(category1);
		return updateCategory;
	}

	@Override
	public CategoryDto getCategotyById(int id) {
		Category category = this.cate.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		return this.categoryTODto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCategory = this.cate.findAll();
		List<CategoryDto> allCategoryDtos = allCategory.stream().map(cr-> this.categoryTODto(cr)).collect(Collectors.toList());
		return allCategoryDtos;
	}

	@Override
	public void deleteCategory(int id) {
		Category category = this.cate.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		this.cate.delete(category);
		
	}

	public Category DtoTOCategory(CategoryDto catedto) {
		Category category = this.model.map(catedto,Category.class);
		return category;
	}
	
	public CategoryDto categoryTODto(Category category) {
		CategoryDto categoryDto = this.model.map(category,CategoryDto.class);
		return categoryDto;
	}
	

}
