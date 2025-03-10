package com.capgemini.client;

import com.capgemini.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name="BOOK-MANAGEMENT", url="http://localhost:8083")
public interface BookManagementFeignClient {

    @GetMapping("/api/books/welcome")
    public String getWelcomeMsg();

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id);

    @GetMapping("/api/books/all")
    public ResponseEntity<List<Book>> getAllBooks();

    @PostMapping("/api/books/create")
    public ResponseEntity<Book> addBook(@RequestBody Book book);

    @PutMapping("/api/books/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book);

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id);

    @GetMapping("/api/books/genre/{genre}")
    public ResponseEntity<List<Book>> findBooksByGenre(@PathVariable String genre);

    @GetMapping("/api/books/title/{title}")
    public ResponseEntity<List<Book>> findBooksByTitle(@PathVariable String title);

    @GetMapping("/api/books/author/{author}")
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable String author);

    @GetMapping("/api/books/language/{language}")
    public ResponseEntity<List<Book>> findBooksByLanguage(@PathVariable String language);

    @GetMapping("/api/books/location/{location}")
    public ResponseEntity<List<Book>> findBooksByLocation(@PathVariable String location);

}


