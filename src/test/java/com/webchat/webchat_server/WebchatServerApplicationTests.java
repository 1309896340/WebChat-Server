package com.webchat.webchat_server;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webchat.webchat_server.service.UserService;
import com.webchat.webchat_server.type.ServiceState;

// import com.webchat.webchat_server.WebchatServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebchatServerApplication.class)
class WebchatServerApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void serviceRegisterRegexTest1() {
		assertTrue(userService.register("sadfasedf12312", "dsfasd3231")==ServiceState.SUCCESS);
	}
	
	
	@Test
	void serviceRegisterRegexTest2() {
		assertTrue(userService.register("sdf%^@%", "dsfasd3231")==ServiceState.SUCCESS);
	}
	
	
	@Test
	void serviceRegisterRegexTest3() {
		assertTrue(userService.register("345sadfasedf12312", "dsfasd3231")==ServiceState.SUCCESS);
	}

	@Test
	void serviceRegisterRegexTest4() {
		assertTrue(userService.register("345sadfasedf12312", "dsfasd3231@%RET#$%")==ServiceState.SUCCESS);
	}

	@Test
	void serviceRegisterRegexTest5() {
		assertTrue(userService.register("34", "dsfasd3231@%RET#$%")==ServiceState.SUCCESS);
	}

}
