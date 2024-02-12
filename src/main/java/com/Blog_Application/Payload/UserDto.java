package com.Blog_Application.Payload;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=3,message = "name must be minimum gerater than 4 character")
	private String name;
	
	@Email(message = "this email not found or this email not valid")
	private String email;
	
	@NotEmpty
	@Size(min=4,max = 10,message = "password must be minimum 4 characters and garater 10 characters")
	private String password;
	
	@NotNull
	private String about;
	

}
