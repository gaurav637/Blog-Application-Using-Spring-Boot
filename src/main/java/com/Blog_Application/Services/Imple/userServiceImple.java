package com.Blog_Application.Services.Imple;

import java.util.List;

import org.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Blog_Application.Exception.*;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.UserDto;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Services.UserServices;

public class userServiceImple implements UserServices{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = DtoToUser(userDto);
		User saveUser = userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateuser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User user1 = userRepo.save(user);
		UserDto userdto1 = userToDto(user1);
		return userdto1;
	}

	@Override
	public UserDto getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAlluser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}
	
	public User DtoToUser(UserDto userdto) {
		User user = new User();
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
