package fr.u.picardie.m2.d605.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.u.picardie.m2.d605.web.api.client.bean.User;
import fr.u.picardie.m2.d605.web.api.client.services.UserApiService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserApiService securityApiService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = securityApiService.findByLogin(username);
		return user;
	}

}
