package com.john.test;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;

	public UserDataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) {
		if (userRepository.count() > 0) {
			return;
		}

		List<User> sampleUsers = List.of(
			createUser("Alice Zhang", "alice.zhang@example.com"),
			createUser("Brian Chen", "brian.chen@example.com"),
			createUser("Cindy Liu", "cindy.liu@example.com")
		);

		userRepository.saveAll(sampleUsers);
	}

	private User createUser(String name, String email) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		return user;
	}
}
