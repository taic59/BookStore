package com.bookstore.javaspringdemo.repositories;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import com.bookstore.javaspringdemo.models.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username); 

}
