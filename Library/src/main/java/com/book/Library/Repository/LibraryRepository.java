package com.book.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.Library.Entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer>  {

}
