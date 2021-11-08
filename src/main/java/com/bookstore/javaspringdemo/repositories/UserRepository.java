package com.bookstore.javaspringdemo.repositories;
import java.util.Optional;

import com.bookstore.javaspringdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username); 

}
