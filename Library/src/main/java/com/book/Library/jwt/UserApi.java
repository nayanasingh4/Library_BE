package com.book.Library.jwt;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.Library.Entity.User;
import com.book.Library.Exception.ResourceNotFoundException;
import com.book.Library.Repository.UserRepository;



@RestController
public class UserApi {
	
@Autowired private UserRepository userRepository;
@Autowired private UserService userService;


    
    @PutMapping("/user/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user,
			@PathVariable("userId") int userId) {
		try {
			this.userService.updateUser(user, userId);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested user cannot be updated as it is not present in the list");
	}
    
    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
		try {
			this.userService.deleteUserById(userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException(
				"The requested user cannot be deleted as it is not present in the list");
	}

}