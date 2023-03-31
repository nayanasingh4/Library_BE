package com.book.Library.jwt;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.Library.Entity.User;
import com.book.Library.Repository.UserRepository;



@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 

	public User save(User user) {
	        String rawPassword = user.getPassword();
	        String encodedPassword = passwordEncoder.encode(rawPassword);
	        user.setPassword(encodedPassword);
	        
	        return userRepository.save(user);
	    }
	
	// update user by id
		public void updateUser(User user, int userId) {
			user.setUserId(userId);
			userRepository.saveAndFlush(user);
		}
		
	// delete user by id
		public void deleteUserById(int userId) {
			userRepository.deleteById(userId);
		}
}
