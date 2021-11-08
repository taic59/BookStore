package com.bookstore.javaspringdemo.services;
import java.util.List;
import com.bookstore.javaspringdemo.models.User;
import com.bookstore.javaspringdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
	private UserRepository usersRepository;
	
	public List<User> getAllUsers(){
		return usersRepository.findAll();
	}

	public void addUser(User user){
		usersRepository.save(user);
	}

	public void deleteUser(Integer id) {
		usersRepository.deleteById(id);
	}
}

