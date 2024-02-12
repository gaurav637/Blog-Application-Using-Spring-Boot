package com.Blog_Application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.Category;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Entities.User;

public interface postRepo extends JpaRepository<Post, Integer>{

	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
}
