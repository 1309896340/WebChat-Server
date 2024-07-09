package com.webchat.webchat_server;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.service.impl.UserServiceImpl;

// import com.webchat.webchat_server.WebchatServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebchatServerApplication.class)
class WebchatServerApplicationTests {
	@Autowired
	private UserService userService;
	@Test
	void serviceRegisterRegexTest() {
		userService.register("sadfasedf12312", "dsfasd3231");
	}

}
