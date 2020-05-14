package org.htk.bot.validator;

import org.htk.bot.models.UserHtk;
import org.htk.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 
 * @author osvaldoairon
 *
 */
@Component
public class UserValidator {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * verifica se ja existe um user com o mesmo username;
	 * @param user
	 * @return
	 */
	public boolean validateCreateUser(UserHtk user) {
		try {
			UserHtk u = userRepository.validateExistenceUser(user.getUsername());
			return (u!=null ? true : false);
			
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	

}
