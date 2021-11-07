package com.bookstore.javaspringdemo.models;

import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Books {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String author;
	private Double price;
	@Column(name = "is_recommended", nullable = false)
	private Boolean isRecommended;	

	public Books() {
		super();
	}
	
	public Books(Integer id, String name, String author, Double price, Boolean isRecommended) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.author = author;
		this.isRecommended = isRecommended;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getIs_recommended() {
		return isRecommended;
	}
	public void setIs_recommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
		
}




