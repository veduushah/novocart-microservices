package com.novacart.auth.config;

import com.novacart.auth.entity.User;
import com.novacart.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        seedDefaultUsers();
    }

    private void seedDefaultUsers() {
        // Create default admin user if it doesn't exist
        if (!userRepository.existsByEmail("ramesh@admin.com")) {
            User adminUser = new User(
                "Ramesh Admin",
                "ramesh@admin.com",
                passwordEncoder.encode("ramesh@admin")
            );
            userRepository.save(adminUser);
            System.out.println("Created default admin user: ramesh@admin.com with password: ramesh@admin");
        }

        // Create a sample shopper if it doesn't exist
        if (!userRepository.existsByEmail("shopper@novocart.in")) {
            User testUser = new User(
                "NovaCart Shopper",
                "shopper@novocart.in",
                passwordEncoder.encode("password123")
            );
            userRepository.save(testUser);
            System.out.println("Created sample shopper: shopper@novocart.in");
        }
    }
}
