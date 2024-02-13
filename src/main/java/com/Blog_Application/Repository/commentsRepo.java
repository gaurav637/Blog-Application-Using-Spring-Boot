package com.Blog_Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.Comment;

public interface commentsRepo extends JpaRepository<Comment, Integer> {

}
