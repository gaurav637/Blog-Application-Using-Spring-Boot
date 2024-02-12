package com.Blog_Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.Category;

public interface categoryRepo extends JpaRepository<Category, Integer> {

}
