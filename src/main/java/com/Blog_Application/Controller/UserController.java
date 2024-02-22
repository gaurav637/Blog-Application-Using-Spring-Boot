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
import com.Blog_Application.BlogServices.UserServices;
import com.Blog_Application.Payload.UserDto;
import com.Blog_Application.Payload.apiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("home/api")
public class UserController {

	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("USER")
	public ResponseEntity<UserDto> createUserController( @Valid @RequestBody UserDto user) {
		UserDto user1 = userServices.createUser(user);
		return new ResponseEntity<>(user1,HttpStatus.CREATED);
	}
	
	@PutMapping("USER/{userId}")
	public ResponseEntity<UserDto> updateUserData(@Valid @RequestBody UserDto user , @PathVariable("userId") int id){
		UserDto user1 = userServices.updateuser(user, id);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}
	
	
	@GetMapping("USER/{USERId}")
	public ResponseEntity<UserDto> getUserByIdInController(@PathVariable("USERId") int userId){
		
		return ResponseEntity.ok(userServices.getUserById(userId));
	}
	
	@GetMapping("USER")
	public ResponseEntity<List<UserDto>> getAllUserInController(){
		return ResponseEntity.ok(userServices.getAlluser());
	}
	
	@DeleteMapping("USER/{userId}")
	public ResponseEntity<apiResponse> deleteUserByIdInController(@PathVariable("userId") int id){
		this.userServices.deleteUser(id);
		return new ResponseEntity(new apiResponse("User deleted successfully",true),HttpStatus.OK);
	
	}
	
	
}
