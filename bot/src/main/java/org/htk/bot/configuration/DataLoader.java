package org.htk.bot.configuration;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.htk.bot.models.Privilege;
import org.htk.bot.models.Role;
import org.htk.bot.models.UserHtk;
import org.htk.bot.repository.RoleRepository;
import org.htk.bot.services.UserService;
import org.htk.bot.util.MyDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{

	boolean s = false;
	
	private final UserService userService;

	private final RoleRepository roleRepository;
	
	@Autowired
	public DataLoader(UserService userService, RoleRepository roleRepository) {
		this.userService=userService;
		this.roleRepository = roleRepository;
	}
	
	protected static final String ADMIN="ROLE_ADMIN";
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(s) {
			return ;
		}
		
		Privilege privilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege privilege_write = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		
		Collection<Privilege> adminPrivileges = Arrays.asList(privilege,privilege_write);
		Role admin = createRoleIfNotFound("ROLE_ADMIN" , adminPrivileges);
		Role user = createRoleIfNotFound("ROLE_USER" , Arrays.asList(privilege));
		
		Role r = this.userService.getRoleByName("ROLE_ADMIN");

		if(this.userService.searchUserByIdentifier()) {
			UserHtk u = new UserHtk();
			u.setPassword("$2a$10$WwnEzg0ppWn1q1qBwgnMEe5PN5m.a00VKrwtg2HRvBrcirJPK90b2");
			u.setUsername("admin");
			u.setEmail("osvaldo.airon@dce.ufpb.br");
			u.setCreat_at(MyDateTime.getDate());
			u.setRoles(Arrays.asList(this.roleRepository.findRoleByNameParam(ADMIN)));
			this.userService.addUser(u);
		}
		s = true;
		
		
	}
	
	
	
	private void insertRelationAdmin(Long user_id, Long role_id){
		this.userService.insertRelationUser(user_id,role_id);
	}
	
	@Transactional
	private Role createRoleIfNotFound(String param_role , Collection<Privilege> collection) {
		Role rol = this.userService.getRoleByName(param_role);
		if(rol == null) {
			Role r = new Role(param_role);
			r.setPrivileges(collection);
			this.userService.insertRoleEntity(r);
		}
		return rol;
	}
	
	
	@Transactional
	private Privilege createPrivilegeIfNotFound(String param_privilege) {
		Privilege p = this.userService.getPrivilegeByName(param_privilege);
		
		if(p == null) {
			Privilege priv = new Privilege(param_privilege);
			this.userService.insertPrivilegeEntity(priv);
		}
		return p;
	}
	

}
