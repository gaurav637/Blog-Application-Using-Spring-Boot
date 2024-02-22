package com.Blog_Application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Blog_Application.BlogServices.postServices;
import com.Blog_Application.Payload.apiResponse;
import com.Blog_Application.Payload.postDto;

@RestController
@RequestMapping("/home/api")
public class postController {
	
	@Autowired
	private postServices pServices;
	
	@PostMapping("user/{userId}/category/{categoryId}/POST")
	public ResponseEntity<postDto> uploadPost(
			@RequestBody postDto post,
			@PathVariable int userId,
			@PathVariable int categoryId){
		postDto post1 = this.pServices.createPost(post, userId, categoryId);
		return new ResponseEntity<postDto>(post1,HttpStatus.CREATED);
	}
	
	@PutMapping("POST/{postId}")
	public ResponseEntity<postDto> updatePostInController(@RequestBody postDto psDto,@PathVariable("postId") int id){
		postDto updateDto = this.pServices.updatePost(psDto, id);
		return new ResponseEntity<postDto>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("POST/{postId}")
	public ResponseEntity<postDto> getSinglePostInController(@PathVariable("postId") int id){
		postDto pDto = this.pServices.getByIdPost(id);
		return new ResponseEntity<postDto>(pDto,HttpStatus.OK);
	}
	
	@GetMapping("POST")
	public ResponseEntity<List<postDto>> getAllPostInController(){
		List<postDto> psDtos = pServices.getAllPost();
		return new ResponseEntity<>(psDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("POST/{postId}")
	public apiResponse deletePostInControlller(@PathVariable("postId") int id) {
		this.pServices.delete(id);
		apiResponse apr = new apiResponse("this post deleted successfully",true);
		return apr;
	}
	
	@GetMapping("user/{userId}/POST")
	public ResponseEntity<List<postDto>> getPostByUser(@PathVariable("userId") int id){
		List<postDto> allPostInUser = this.pServices.getPostByUser(id);
		return new ResponseEntity<List<postDto>>(allPostInUser,HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}/POST")
	public ResponseEntity<List<postDto>> getPostByCategory(@PathVariable("categoryId") int id){
		List<postDto> allpostInCategory = this.pServices.getPostByCategory(id);
		return new ResponseEntity<List<postDto>>(allpostInCategory,HttpStatus.OK);
	}

}
