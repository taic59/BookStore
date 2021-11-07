package com.bookstore.javaspringdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
@SpringBootTest
class JavaSpringDemoApplicationTests {
	int randomServerPort;
	String url="http://localhost:";
    RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

}
