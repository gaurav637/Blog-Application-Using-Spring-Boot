package com.Blog_Application.BlogServices;

import com.Blog_Application.Payload.CommentDto;

public interface commentsServices {
	public CommentDto addCommentDto(CommentDto cDto,int postId);
	public void deleteComment(int id);
}
