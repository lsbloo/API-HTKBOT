package org.htk.bot.services;

import org.htk.bot.models.Privilege;
import org.htk.bot.models.Role;
import org.htk.bot.models.UserHtk;
import org.htk.bot.repository.PrivilegeRepository;
import org.htk.bot.repository.RoleRepository;
import org.htk.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.htk.bot.validator.UserValidator;
import org.htk.bot.util.MyDateTime;

import java.util.Arrays;

@Service
public class UserService {
	
	protected static final String USER="ROLE_USER";
	
	private UserRepository userRepository;
	
	private UserValidator userValidator;
	
	private final PrivilegeRepository privilegeRepository;
	
	private final RoleRepository roleRepository;
	

	@Autowired
	public UserService(UserRepository userRepository, UserValidator userValidator,PrivilegeRepository privilegeRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.userValidator=userValidator;
		this.privilegeRepository=privilegeRepository;
		this.roleRepository=roleRepository;
	}

	
	public void addUser(UserHtk user) {
		this.userRepository.save(user);
	}
	
	
	public boolean createUser(UserHtk user) {
		user.setCreat_at(MyDateTime.getDate());
		boolean validate = this.userValidator.validateCreateUser(user);
		if(!validate) {
			user.setRoles(Arrays.asList(this.roleRepository.findRoleByNameParam(USER)));
			String pass = user.getPassword();
			user.setPassword(passwordEncoder().encode(pass));
			this.userRepository.save(user);
			return true;
		}
		return false;
	}
	
	
	/**
	 * creates a new bcryptpassword object and encrypts the user's password.
	 * 
	 * @return new instance of BcryptPasswordEncoder;
	 */
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * return privilege object by search name;
	 * @param name
	 * @return
	 */
	public Privilege getPrivilegeByName(String name) {
		return this.privilegeRepository.findPrivilegeByNameParam(name);
	}
	
	/**
	 * Insert new Privilege;
	 * @param name
	 * @param id
	 */
	public void insertPrivilege(String name, int id) {
		this.privilegeRepository.insertPrivilege(id, name);
	}
	
	/**
	 * Insert new Object Privilege;
	 * @param privilege
	 */
	public void insertPrivilegeEntity(Privilege privilege) {
		this.privilegeRepository.save(privilege);
	}
	
	
	/**
	 * return role object by search name;
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name) {
		return this.roleRepository.findRoleByNameParam(name);
	}
	
	/**
	 * Insert new Object Privilege;
	 * @param
	 */
	public void insertRoleEntity(Role role) {
		this.roleRepository.save(role);
	}
	
	public int getRoleIdByUserId(Long user_id) {
		return this.roleRepository.getIdRoleAssociateUser(user_id);
	}
	
	public String getRoleById(Integer role_id) {
		return this.roleRepository.getRoleById(role_id);
	}


	public void insertRelationUser(Long user_id, Long role_id) {
		// TODO Auto-generated method stub
		this.userRepository.insertRelationUser(user_id,role_id);
	}
	
	public boolean searchUserByIdentifier(){
		UserHtk u = this.userRepository.validateExistenceUser("admin");
		if(u == null){
			return true;
		}
		return false;
	}
	
}
