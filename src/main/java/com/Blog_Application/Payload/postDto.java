package com.Blog_Application.Payload;


import java.util.Date;

import com.Blog_Application.Entities.Category;
import com.Blog_Application.Entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postDto {
	
	private String title;
	private String content;
	private String imageName;
	private Date uploadDate;
	private CategoryDto category;
	private UserDto user;

}
