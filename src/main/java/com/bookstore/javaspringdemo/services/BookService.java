package com.bookstore.javaspringdemo.services;
import java.util.List;
import com.bookstore.javaspringdemo.models.Books;
import com.bookstore.javaspringdemo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Books> getAllBooks(){
		return bookRepository.findAll();
    }
	
	public Books getBooksById(Integer id){
		return bookRepository.findById(id).get();
    }
    
	public List<Books> getSortedBooks(String field1,String field2) {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, field1).and(Sort.by(Sort.Direction.ASC, field2)));
    }

	public void addBook(Books book){
		bookRepository.save(book);
	}

	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
	}
}
