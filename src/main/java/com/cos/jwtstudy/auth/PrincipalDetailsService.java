package com.cos.jwtstudy.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.jwtstudy.model.Users;
import com.cos.jwtstudy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// http://localhost:8080/login => 여기서 동작을 안한다.

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService의 loadUserByUsername ");
		
		Users userEntity = userRepository.findByUsername(username);
			
		
		
		return new PrincipalDetails(userEntity);
	}

}
