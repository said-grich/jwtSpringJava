package com.example.jwtSpringJava;

import com.example.jwtSpringJava.entity.Role;
import com.example.jwtSpringJava.entity.User;
import com.example.jwtSpringJava.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtSpringJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringJavaApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService){
	return 	ars->{
		userService.saveRole(new Role(null,"ROLE_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_USER"));
		userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_MANAGER"));
		userService.saveUser(new User(null,"said","grich","saad06","test12345",new ArrayList<>()));
		userService.saveUser(new User(null,"khalid","ma","khalid06","test12345",new ArrayList<>()));
		userService.saveUser(new User(null,"jack","posten","bock21","test12345",new ArrayList<>()));
		userService.saveUser(new User(null,"samir","jackod","ba3lu06","test12345",new ArrayList<>()));
		userService.addRoleToUser("saad06","ROLE_ADMIN");
		userService.addRoleToUser("khalid06","ROLE_USER");
		userService.addRoleToUser("bock21","ROLE_SUPER_ADMIN");
		userService.addRoleToUser("ba3lu06","ROLE_MANAGER");
		userService.getUser("saad06");

		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
