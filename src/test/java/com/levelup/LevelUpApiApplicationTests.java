package com.levelup;

import com.levelup.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LevelUpApiApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private UserRepository userRepository;






}
