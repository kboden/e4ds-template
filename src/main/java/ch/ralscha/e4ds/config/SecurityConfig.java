package ch.ralscha.e4ds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@ImportResource("classpath:spring/security.xml")
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
	
}
