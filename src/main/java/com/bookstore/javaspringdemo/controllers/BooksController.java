package com.bookstore.javaspringdemo.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bookstore.javaspringdemo.models.Books;
import com.bookstore.javaspringdemo.responses.BookResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class BooksController {

	@GetMapping("books")
	public ResponseEntity<BookResponse> getAllBooks() {
		try {
			List<Books> books = new ArrayList<Books>();
			RestTemplate restTemplate = new RestTemplate();
			String BASE_URL = "https://scb-test-book-publisher.herokuapp.com/books/recommendation";
			ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(BASE_URL, Object[].class);
			List<Object> recommendationData = Arrays.asList(responseEntity.getBody());
			Gson gson = new Gson();
			List<Integer> recommendationList = new ArrayList<>();
			for (int i = 0; i < recommendationData.size(); i++) {     
				String jsonString = gson.toJson(recommendationData.get(i));
				JsonObject data = new Gson().fromJson(jsonString, JsonObject.class);
				Integer id = data.get("id").getAsInt();
				recommendationList.add(id);
			}
			BASE_URL = "https://scb-test-book-publisher.herokuapp.com/books";
			restTemplate = new RestTemplate();
			responseEntity = restTemplate.getForEntity(BASE_URL, Object[].class);
			List<Object> bookData = Arrays.asList(responseEntity.getBody());
			gson = new Gson();
			for (int i = 0; i < bookData.size(); i++) {     
				String jsonString = gson.toJson(bookData.get(i));
				JsonObject data = new Gson().fromJson(jsonString, JsonObject.class);
				Integer id = data.get("id").getAsInt();
				String name = data.get("book_name").getAsString();
				String author = data.get("author_name").getAsString();
				Double price = data.get("price").getAsDouble();
				Boolean isRecommended = false;
				if(recommendationList.contains(id)) {
					isRecommended = true;
				}
				books.add(new Books(id, name, author, price, isRecommended));
			}			
			Comparator<Books> compareByName = Comparator.comparing(Books::getIs_recommended).reversed().thenComparing(Books::getName);
			List<Books> sortedBooks = books.stream()
				.sorted(compareByName)
				.collect(Collectors.toList());

			return ResponseEntity.ok().body(new BookResponse("200 OK", sortedBooks));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new BookResponse("500 Error", e));
		}
	}
}