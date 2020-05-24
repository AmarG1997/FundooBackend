package com.blabz.fundoo.service;

import java.util.Optional;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blabz.fundoo.dto.LoginDto;
import com.blabz.fundoo.dto.UserDto;
import com.blabz.fundoo.exception.UserException;
import com.blabz.fundoo.model.User;
import com.blabz.fundoo.repository.UserRepo;
import com.blabz.fundoo.utility.Utility;

@Service
public class UserRegistrationImpl implements IUserRegistrationService {

	@Autowired
	UserRepo UserRepo;

	@Autowired
	Utility utility;
	
	@Override
	public User addUser(UserDto userDto) throws MessagingException {
		User user = new User(userDto);
		Optional<User> byEmailId = UserRepo.findByEmail(userDto.email);
		if (byEmailId.isPresent()) {
			throw new UserException(UserException.ExceptionType.INVALID_DATA, "User Already exist");
		}
		UserRepo.save(user);
		String token = utility.createToken(userDto.email);
		System.out.println("Token is ---->" + token);
		utility.sendEmail(userDto.email, token);
		return user;
	}

	@Override
	public String userLogin(LoginDto loginDto) {
		Optional<User> byEmailId = UserRepo.findByEmail(loginDto.email);
		if (byEmailId.isPresent() && loginDto.password.matches(byEmailId.get().getPassword())) {
			return "Login Successfull";
		}
		return "Incorrect email and password";
	}

	@Override
	public String checkEmail(LoginDto loginDto) {
		Optional<User> byEmailId = UserRepo.findByEmail(loginDto.email);
		if (byEmailId.isPresent()) {
			return "Present";
		}
		return "Enter valid email";
	}

	@Override
	public String verifyUser(String token) {
		String validateEmail = utility.getUserToken(token);
		if(validateEmail!=null) {
			Optional<User> user = UserRepo.findByEmail(validateEmail);
			   user.get().setVerify(true);
			   UserRepo.save(user.get());
			return "Verify user successfully";
		}
		return "Email id is not exist";
	}
}
