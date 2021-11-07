package com.bookstore.javaspringdemo.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Array;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User implements UserDetails{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String username;
	private String password;
	private String name;
	private String surname;
	@Column(name = "date_of_birth", nullable = false)
    private String date_of_birth;
	private Integer[] books;
	private Integer[] orders;
	
	public User() {
		super();
	}
    
    public User(Integer id, String username, String password, String name, String surname, String date_of_birth, Integer[] books,Integer[] orders) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.books = books;
		this.orders = orders;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@JsonProperty("date_of_birth")
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Integer[] getBooks() {
		return books;
	}
	public void setBooks(Integer[] books) {
		this.books = books;
	}
	public Integer[] getOrders() {
		return orders;
	}
	public void setOrders(Integer[] orders) {
		this.orders = orders;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", books=" + books +
                '}';
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}

