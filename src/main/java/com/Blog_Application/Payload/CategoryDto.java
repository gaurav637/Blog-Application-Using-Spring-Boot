package com.Blog_Application.Payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int id;
	
	@NotEmpty
	@Size(max=100,message="this title not sutable")
	@Column(name="title")
	private String categoryTitle;
	
	@NotNull
	@Column(name="Description")
	private String categoryDescription;

}
