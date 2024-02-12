package com.Blog_Application.Services.Imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog_Application.BlogServices.UserServices;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.UserDto;
import com.Blog_Application.Repository.UserRepo;

@Service
public class userServiceImple implements UserServices{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = DtoToUser(userDto);
		User saveUser = userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateuser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
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
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		List<User>  users = userRepo.findAll();
		List<UserDto> userdto1 =  users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userdto1;
	}

	@Override
	public void deleteUser(int userId) {
		User user  = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
		
	}
	
	public User DtoToUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto,User.class);
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
