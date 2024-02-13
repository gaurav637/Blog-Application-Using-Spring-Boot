package com.Blog_Application.Payload;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.Blog_Application.Entities.Comment;

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
	private Set<CommentDto> comments = new HashSet<>();
}
