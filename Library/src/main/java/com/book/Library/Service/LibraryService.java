package com.book.Library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.Library.Entity.Library;
import com.book.Library.Repository.LibraryRepository;


@Service
public class LibraryService {
	
	@Autowired
	public LibraryRepository libraryRepository;
	
	// add book
		public Library savebook(Library library) {
			return libraryRepository.save(library);
		}
	
	// get book list
		public List<Library> getAllBook() {
			return this.libraryRepository.findAll();
		}
		
	// get book by id
		public Optional<Library> getBookId(int bookId) {
			return libraryRepository.findById(bookId);
		}
		
	// update book
		public void updateBookById(Library library, int bookId) {
			library.setBookId(bookId);
			libraryRepository.saveAndFlush(library);
		}
		
	// delete book by id
		public void deleteBookById(int bookId) {
			libraryRepository.deleteById(bookId);
		}

	

}
