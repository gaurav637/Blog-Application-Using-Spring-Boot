package com.Blog_Application.Services.Imple;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog_Application.BlogServices.postServices;
import com.Blog_Application.Entities.Category;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.postDto;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Repository.categoryRepo;
import com.Blog_Application.Repository.postRepo;


@Service
public class postServicesImple implements postServices {

	@Autowired
	private postRepo pRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private categoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public postDto createPost(postDto postdto,int userId,int categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = DtoToPost(postdto);
		post.setImageName("default.png");
		post.setUploadDate(new Date());
		post.setCategory(category);
		post.setUser(user);		
		Post savedPost = pRepo.save(post);
		return this.PostToDto(savedPost);
	}

	@Override
	public postDto updatePost(postDto postdto, int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		Post pr = pRepo.save(post);
		return this.PostToDto(pr);
	}

	@Override
	public postDto getByIdPost(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return this.PostToDto(post);
	}

	@Override
	public List<postDto> getAllPost() {
		List<Post> allPosts = pRepo.findAll();
		List<postDto> allDtosPosts = allPosts.stream().map(psa-> (PostToDto(psa))).collect(Collectors.toList());
		return allDtosPosts;
	}

	@Override
	public void delete(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		this.pRepo.delete(post);
		
	}

	@Override
	public List<postDto> getPostByCategory(int id) {
		
		Category ct = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		List<Post> catePosts = pRepo.findByCategory(ct);
		List<postDto> pstDtos = catePosts.stream().map(ps-> (PostToDto(ps))).collect(Collectors.toList());
		return pstDtos;
	}

	@Override
	public List<postDto> getPostByUser(int id) {
		
		User us = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		List<Post> userpst = pRepo.findByUser(us);
		List<postDto> userDtos = userpst.stream().map(usp-> (PostToDto(usp))).collect(Collectors.toList());
		return userDtos;
	}
	
	public Post DtoToPost(postDto pd) {
		Post prPost = this.model.map(pd,Post.class);
		return prPost;
	}
	
	public postDto PostToDto(Post post) {
		postDto pDto = model.map(post, postDto.class);
		return pDto;
	}

}
