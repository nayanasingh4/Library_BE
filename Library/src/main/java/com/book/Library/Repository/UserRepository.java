package com.book.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.Library.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

	public User findByEmailId(String email);



}
