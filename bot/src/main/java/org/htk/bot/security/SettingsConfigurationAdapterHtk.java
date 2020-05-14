package org.htk.bot.security;




import org.htk.bot.configuration.MyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SettingsConfigurationAdapterHtk 
	extends WebSecurityConfigurerAdapter{

	/**
	@Autowired
    private MyDetailService userDetailsService;
	 * 
	 */
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors().and().csrf().disable();
		
		
		
	}

	/**
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	//	  auth.userDetailsService(userDetailsService);
	//	  System.err.println((userDetailsService.loadUserByUsername("osvaldo")));
	
		
		// auth.inMemoryAuthentication().
		 // withUser("osvaldo").password("{noop}123").roles("USER") .and().
		 // withUser("admin").password("{noop}password").roles("ADMIN"); 
		   
}
	 */
	

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/**
	 * @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();

	}
	 */
	
	
	
	 

	
	
}
