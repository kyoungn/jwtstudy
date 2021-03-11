package com.cos.jwtstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

import com.cos.jwtstudy.filter.JwtAuthenticationFilter;
import com.cos.jwtstudy.filter.JwtAuthrorizationFilter;
import com.cos.jwtstudy.filter.MyFilter3;
import com.cos.jwtstudy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CorsFilter corsFilter;
	private final UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEnc() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//시큐리티에 적용된 필터가 일반 필터보다 먼저 실행된다.
		//SecurityFilterChain
//		http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);
		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); //시큐리티 진입전에 필터적용
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //session을 사용안함
			.and()
			.addFilter(corsFilter) // @CrossOrigin(인증x), 시큐리티필터에 등록 인증(o)
			.formLogin().disable()  //form login을 사용안함
			.httpBasic().disable() //header  Authorization : ID, PW 전송 https 사용
			.addFilter(new JwtAuthenticationFilter(authenticationManager())) //AuthencationManager를 던져야함.
			.addFilter(new JwtAuthrorizationFilter(authenticationManager(), userRepository)) //AuthencationManager를 던져야함.
			.authorizeRequests()
			.antMatchers("/api/v1/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/api/v1/manager/**")
			.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/api/v1/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();
			
	}
}
