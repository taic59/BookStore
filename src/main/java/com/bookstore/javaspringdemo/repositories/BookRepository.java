package com.bookstore.javaspringdemo.repositories;
import java.util.List;

import com.bookstore.javaspringdemo.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Books, Integer> {
}
