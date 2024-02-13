package com.Blog_Application.Services.Imple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog_Application.BlogServices.commentsServices;
import com.Blog_Application.Entities.Comment;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.CommentDto;
import com.Blog_Application.Repository.commentsRepo;
import com.Blog_Application.Repository.postRepo;

@Service
public class commentServiceImple implements commentsServices {

	@Autowired
	private commentsRepo cRepo;
	
	@Autowired
	private postRepo psPost;
	
	@Autowired
	private ModelMapper model;
	
	public CommentDto addCommentDto(CommentDto cDto, int postId) {
		Post post = this.psPost.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		Comment com = model.map(cDto, Comment.class);
		com.setPost(post);
		Comment saveComment = cRepo.save(com);
		return model.map(saveComment, CommentDto.class);
	}


	public void deleteComment(int id) {
        Comment comment = this.cRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment","id",id));
        this.cRepo.delete(comment);
		
	}

}
