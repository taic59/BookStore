package com.bookstore.javaspringdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
@SpringBootTest
class JavaSpringDemoApplicationTests {
	int randomServerPort;
	String url="http://localhost:";
    RestTemplate restTemplate = new RestTemplate();
    

	@Test
	void contextLoads() {
	}

}
