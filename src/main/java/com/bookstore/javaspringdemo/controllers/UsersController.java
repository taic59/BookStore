package com.bookstore.javaspringdemo.controllers;
import com.bookstore.javaspringdemo.models.User;
import com.bookstore.javaspringdemo.repositories.UserRepository;
import com.bookstore.javaspringdemo.responses.UserResponse;
import com.bookstore.javaspringdemo.services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@RestController
@RequestMapping("/")
public class UsersController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping("users")
	public ResponseEntity<UserResponse> getUserDetail(Authentication authentication) {
		try {
			User test = userRepository.findByUsername(authentication.getName()).get();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", test.getName());
			map.put("surname", test.getSurname());
			map.put("date_of_birth", test.getDate_of_birth());
			map.put("books", test.getBooks());
			return ResponseEntity.ok().body(new UserResponse("200 OK", map));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new UserResponse("500 Error", e));
		}
	}
    
	@PostMapping("users")
	public ResponseEntity<UserResponse> createUser(@RequestBody User request) {
		try {
			List<User> users = userRepository.findAll();
			for (User user : users) {
				if (user.equals(request)) {
					return ResponseEntity.badRequest().body(new UserResponse("400 Error", "User already exists"));
				}
			}
			List<String> splitted = Arrays.asList(request.getUsername().split("\\."));
			String name, surname;
			if(splitted.size() == 2){
				name = splitted.get(0);
				surname = splitted.get(1);
			}
			else{
				return ResponseEntity.badRequest().body(new UserResponse("400 Error", "Username format: name.surname"));
			}
			request.setName(name);
			request.setSurname(surname);
			// request.setPassword(passwordEncoder.encode(request.getPassword()));
			
			userRepository.save(request);
			return ResponseEntity.ok().body(new UserResponse("200 OK", null));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new UserResponse("500 Error", e));
		}
	}

	@DeleteMapping("users")
	public ResponseEntity<UserResponse> deleteUser(Authentication authentication) {
		try {
			User test = userRepository.findByUsername(authentication.getName()).get();
			userService.deleteUser(test.getId());
			return ResponseEntity.ok().body(new UserResponse("200 OK", null));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new UserResponse("500 Error", e));
		}
	}

	@PostMapping("users/orders")
	public ResponseEntity<UserResponse> saveUserBookOrder(@RequestBody User request, Authentication authentication) {
		try {
			User test = userRepository.findByUsername(authentication.getName()).get();
			Double totalPrice = 0.0;
			Integer[] orders = request.getOrders();
			test.setBooks(orders);
			userRepository.save(test);
	
			RestTemplate restTemplate = new RestTemplate();
			Gson gson = new Gson();
			String BASE_URL = "https://scb-test-book-publisher.herokuapp.com/books";
			ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(BASE_URL, Object[].class);
			List<Object> bookData = Arrays.asList(responseEntity.getBody());
			for (int i = 0; i < orders.length; i++) {     
				String jsonString = gson.toJson(bookData.get(orders[i]));
				JsonObject data = new Gson().fromJson(jsonString, JsonObject.class);
				Double price = data.get("price").getAsDouble();
				totalPrice += price;
			}
			Map<String, Double> result  = new HashMap<String, Double>();
			result.put("price", totalPrice);
			return ResponseEntity.ok().body(new UserResponse("200 OK", result));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new UserResponse("500 Error", e));
		}
	}
}
