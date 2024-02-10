package com.Blog_Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
