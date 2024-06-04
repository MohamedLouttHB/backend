package com.sebn.backend;

import com.sebn.backend.security.user.Role;
import com.sebn.backend.security.user.User;
import com.sebn.backend.security.user.repository.RoleRepository;
import com.sebn.backend.security.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {

			Role roleSuperAdmin = roleRepository.findByName("SUPER_ADMIN");
			if(roleSuperAdmin == null)
			roleRepository.save(new Role(null, "SUPER_ADMIN"));

			Optional<User> firstUser = userRepository.findByEmail("test@test.com");
			if (firstUser.isEmpty())
				userRepository.save(new User(null, "tester", "tester", "test@test.com", passwordEncoder.encode("test"), roleSuperAdmin));


		};
	}

}
