package org.htk.bot.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;
import javax.transaction.Transactional;

import org.htk.bot.models.UserHtk;
import org.htk.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class MyDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		// TODO Auto-generated method stub
		UserHtk u = this.userRepository.validateExistenceUser(username);
		if(u==null) {
			throw new UsernameNotFoundException(username);
		}else {
			
			
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			
			final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
			 
			boolean re = getAuthorities(u.getRoles(),u.getId());
			if(re) {
				UserDetails user = userBuilder
			            .username(u.getUsername())
			            .password(u.getPassword())
			            .roles("USER")
			            .build();
				
				return user;
			}else {
				UserDetails user = userBuilder
			            .username(u.getUsername())
			            .password(u.getPassword())
			            .roles("ADMIN")
			            .build();
				
				return user;
			}
			
		}
		
		
	}


	

	private boolean getAuthorities(
	      Collection<org.htk.bot.models.Role> collection , Integer id) {
			
			
	        for(GrantedAuthority g : getGrantedAuthorities(collection,id)){
	        	return g.getAuthority() != "ROLER_USER"? true : false;
	        	
	        }
	      return true;
	}
	    private List<GrantedAuthority> getGrantedAuthorities(Collection<org.htk.bot.models.Role> collection , Integer id) {
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        
	        for (org.htk.bot.models.Role privilege : collection) {
	            authorities.add(new SimpleGrantedAuthority(privilege.getAuthority()));
	        }
	        
	        return authorities;
	    }
	    
	    
	    private List<String> getPrivileges(Collection<Role> roles) {
	        List<String> privileges = new ArrayList<>();
	        List<String> collection = new ArrayList<>();
	        for (Role role : roles) {
	            collection.add(((GrantedAuthority) role).getAuthority());
	        }
	        if(collection!=null) {
	        	for(int i =0 ; i<collection.size();i++) {
	        		System.err.println(privileges.get(i));
	        		privileges.add(collection.get(i));
	        	}
	        }
	        
	        return privileges;
	        
	    }
}
