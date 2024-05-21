package com.sebn.backend;

import com.sebn.backend.security.user.Role;
import com.sebn.backend.security.user.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RoleRepository roleRepository){
		return args -> {
			Role roleUser = roleRepository.findByName("USER");
			if(roleUser == null)
			roleRepository.save(new Role(null, "USER"));

			Role roleAdmin = roleRepository.findByName("ADMIN");
			if(roleAdmin == null)
			roleRepository.save(new Role(null, "ADMIN"));

			Role roleSuperAdmin = roleRepository.findByName("SUPER_ADMIN");
			if(roleSuperAdmin == null)
			roleRepository.save(new Role(null, "SUPER_ADMIN"));

		};
	}

}
