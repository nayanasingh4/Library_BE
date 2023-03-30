package com.book.Library.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.Library.Entity.Library;
import com.book.Library.Exception.ResourceNotFoundException;
import com.book.Library.Repository.LibraryRepository;
import com.book.Library.Service.LibraryService;


@RestController
@CrossOrigin
public class LibraryController {

	@Autowired
	public LibraryRepository libraryRepository;

	@Autowired
	public LibraryService libraryService;
	
	@GetMapping("/book")
	public ResponseEntity<List<Library>> getBook() {
		List<Library> list = libraryService.getAllBook();
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("Employee list is empty");

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<Optional<Library>> getEmployeeById(@PathVariable("bookId") int bookId) {
		Optional<Library> library = libraryService.getBookId(bookId);
		if (library == null) {
			throw new ResourceNotFoundException("Book is not present with the id:" + bookId);
		}
		return ResponseEntity.of(Optional.of(library));
	}
	
	@PostMapping("/book")
	public ResponseEntity<Library> addPatient(@RequestBody Library library) {
		Library b = null;
		try {
			b = this.libraryService.savebook(library);
			System.out.println(libraryService);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PutMapping("/book/update/{bookId}")
	public ResponseEntity<Library> updateBook(@RequestBody Library library,
			@PathVariable("bookId") int bookId) {
		try {
			this.libraryService.updateBookById(library, bookId);
			return ResponseEntity.ok().body(library);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested book cannot be updated as it is not present in the list");
	}
	
	@DeleteMapping("/book/delete/{bookId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("bookId") int bookId) {
		try {
			this.libraryService.deleteBookById(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException(
				"The requested book cannot be deleted as it is not present in the list");
	}
	
}

