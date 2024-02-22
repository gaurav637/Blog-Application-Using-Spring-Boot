package com.Blog_Application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog_Application.BlogServices.categoryService;
import com.Blog_Application.Payload.CategoryDto;
import com.Blog_Application.Payload.apiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("home/api")
public class categoryController {
	
	@Autowired
	private categoryService cService;
	
	@PostMapping("Category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cDto) {
		CategoryDto newCategory = this.cService.createCategory(cDto);
		return new  ResponseEntity<>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("Category/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategoryInController(@Valid @RequestBody CategoryDto cDto,@PathVariable("categoryId") int id){
		CategoryDto updateDto = this.cService.updateCategory(cDto, id);
		return new ResponseEntity<>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("Category/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategoryInController(@PathVariable("categoryId") int id){
		CategoryDto category = this.cService.getCategotyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@GetMapping("Category")
	public ResponseEntity<List<CategoryDto>> getAllCategoryInController(){
		List<CategoryDto> allDto = this.cService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(allDto);
	}
	
	
	@DeleteMapping("Category/{categoryId}")
	public apiResponse deleteCategoryInController(@PathVariable("categoryId") int id) {
		this.cService.deleteCategory(id);
		apiResponse ar = new apiResponse("categories deleted successfully",true);
		return ar;
	}

}
