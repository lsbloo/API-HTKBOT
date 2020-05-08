package org.htk.bot.services;

import org.htk.bot.models.UserHtk;
import org.htk.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.htk.bot.validator.UserValidator;
import org.htk.bot.util.MyDateTime;

@Service
public class UserService {
	
	
	private UserRepository userRepository;
	
	private UserValidator userValidator;
	

	@Autowired
	public UserService(UserRepository userRepository, UserValidator userValidator) {
		this.userRepository = userRepository;
		this.userValidator=userValidator;
	}

	
	
	public boolean createUser(UserHtk user) {
		user.setCreat_at(MyDateTime.getDate());
		boolean validate = this.userValidator.validateCreateUser(user);
		if(!validate) {
			this.userRepository.save(user);
			return true;
		}
		return false;
	}
	
	
}
