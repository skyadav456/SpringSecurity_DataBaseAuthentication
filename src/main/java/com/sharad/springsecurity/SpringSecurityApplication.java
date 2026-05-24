package com.sharad.springsecurity;

import com.sharad.springsecurity.entity.UserEntity;
import com.sharad.springsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    // CommandLine Runner to save user details
   /* @Bean
    CommandLineRunner run(PasswordEncoder encoder, UserRepository repository) {

        return args -> {

            UserEntity user = new UserEntity();
            user.setUsername("sharad");
            user.setPassword(encoder.encode("1234"));
            user.setRole("USER");
            repository.save(user);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole("ADMIN");
            repository.save(admin);
        };
    }*/
}
