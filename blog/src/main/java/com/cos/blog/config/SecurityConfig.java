package com.cos.blog.config;

import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;

	// 암호를 해쉬로 만든다(encode한다)
	@Bean // Ioc가 되어 return 값을 스프링이 관리한다
	public BCryptPasswordEncoder encoderPWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주고 pwd를 가로채기하는데
	// 해당 pwd가 뭘로 해쉬되어있는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할수 있다
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf().disable() // csrf 토큰 비활성화
		  .authorizeHttpRequests()
		    .antMatchers("/","/auth/**","/js/**","/css/**","/images/**")	// auth 이하의 주소는 허용
			.permitAll()
			.anyRequest()				// 그외는 제한한다
			.authenticated()
		  .and()
		    .formLogin()				// 허용하지 않은 주소를 치면 로그인 페이지로 이동한다
		    .loginPage("/auth/loginForm")
		    .loginProcessingUrl("/auth/loginProc") // 시큐리티가 해당 주소로 오청오는 로그인을 가로채서 대신 로그인한다.
		    .defaultSuccessUrl("/"); // 로그인이 정상일때 해당 주소로 넘어간다
		
		return http.build();
	}
	
}
