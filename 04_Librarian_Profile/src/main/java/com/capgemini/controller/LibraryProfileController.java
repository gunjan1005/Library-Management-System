package com.capgemini.controller;


import com.capgemini.client.BookManagementFeignClient;
import com.capgemini.client.BookingStatusFeignClient;
import com.capgemini.model.Book;
import com.capgemini.model.LibraryProfile;
import com.capgemini.service.LibraryProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/library-profiles")
public class LibraryProfileController {

    @Autowired
    private LibraryProfileService libraryProfileService;

//    @Autowired
//    private BookManagementFeignClient bookManagementFeignClient;
//
//    @Autowired
//    private BookingStatusFeignClient bookingStatusFeignClient;

    @Autowired
    private RestTemplate restTemplate;

/*
    private final String BOOKING_STATUS_URL = "http://localhost:8084/api/books";

   // name="BOOK-MANAGEMENT", url="http://localhost:8083"


    @GetMapping("/books/status/{status}")
    //public ResponseEntity<List<Book>> findBooksByStatus(@PathVariable String status)
    public Map<String, Object>  findBooksByStatus(@PathVariable String status){
        //return  bookingStatusFeignClient.findBooksByStatus(status);
        String url = BOOKING_STATUS_URL + "/status/" + status;
        return restTemplate.getForObject(url, Map.class);
       // return restTemplate.;
    }

    @PutMapping("/books/{id}/status")
    public ResponseEntity<Book> updateBookStatus(@PathVariable Long id, @RequestParam String status){
        return  bookingStatusFeignClient.updateBookStatus(id,status);
    }

    @GetMapping("/librarian")
    public String getLibrarianMsg(){
        String welcomeMsg = bookManagementFeignClient.getWelcomeMsg();
        String greetMessage = " Hey Librairan !!!,  ";
        return  welcomeMsg + greetMessage;
        //return  greetMessage;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Long id) {
        //return bookManagementFeignClient.getBookById(id).getBody();
        Book book = libraryProfileService.getBookById(id);
        return book;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookManagementFeignClient.getAllBooks().getBody();
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return bookManagementFeignClient.addBook(book).getBody();
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookManagementFeignClient.updateBook(id, book).getBody();
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookManagementFeignClient.deleteBook(id).getBody();
    }

    @GetMapping("/books/genre/{genre}")
    public List<Book> findBooksByGenre(@PathVariable String genre) {
        return bookManagementFeignClient.findBooksByGenre(genre).getBody();
    }

    @GetMapping("/books/title/{title}")
    public List<Book> findBooksByTitle(@PathVariable String title) {
        return bookManagementFeignClient.findBooksByTitle(title).getBody();
    }

    @GetMapping("/books/author/{author}")
    public List<Book> findBooksByAuthor(@PathVariable String author) {
        return bookManagementFeignClient.findBooksByAuthor(author).getBody();
    }

    @GetMapping("/books/language/{language}")
    public List<Book> findBooksByLanguage(@PathVariable String language) {
        return bookManagementFeignClient.findBooksByLanguage(language).getBody();
    }

    @GetMapping("/books/location/{location}")
    public List<Book> findBooksByLocation(@PathVariable String location) {
        return bookManagementFeignClient.findBooksByLocation(location).getBody();
    }

*/
    //End point for Book Management Service

    @GetMapping("/books/welcome")
    public ResponseEntity<String> getWelcomeMsg() {
        String msg = libraryProfileService.getWelcomeMsg();
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/books/create")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = libraryProfileService.addBook(book);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/books/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = libraryProfileService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = libraryProfileService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = libraryProfileService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        libraryProfileService.deleteBook(id);
        return ResponseEntity.ok("Book with ID " + id + " has been successfully deleted.");
    }

    @GetMapping("/books/genre/{genre}")
    public ResponseEntity<List<Book>> findBooksByGenre(@PathVariable String genre) {
        List<Book> books = libraryProfileService.findBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/title/{title}")
    public ResponseEntity<List<Book>> findBooksByTitle(@PathVariable String title) {
        List<Book> books = libraryProfileService.findBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable String author) {
        List<Book> books = libraryProfileService.findBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/language/{language}")
    public ResponseEntity<List<Book>> findBooksByLanguage(@PathVariable String language) {
        List<Book> books = libraryProfileService.findBooksByLanguage(language);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/location/{location}")
    public ResponseEntity<List<Book>> findBooksByLocation(@PathVariable String location) {
        List<Book> books = libraryProfileService.findBooksByLocation(location);
        return ResponseEntity.ok(books);
    }

//End point for BookingStatus Service

    @GetMapping("/books/status/{status}")
    public ResponseEntity<List<Book>> findBooksByStatus(@PathVariable String status) {
        List<Book> books = libraryProfileService.findBooksByStatus(status);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/books/{id}/status")
    public ResponseEntity<Book> updateBookStatus(@PathVariable Long id, @RequestParam String status) {
        Book updatedBook = libraryProfileService.updateBookStatus(id, status);
        return ResponseEntity.ok(updatedBook);
    }

//End point for LibrarianProfile Service

    @PostMapping("/create")
    public ResponseEntity<LibraryProfile> createLibraryProfile(@RequestBody LibraryProfile libraryProfile) {
        LibraryProfile createdProfile = libraryProfileService.createLibraryProfile(libraryProfile);
        return ResponseEntity.ok(createdProfile);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LibraryProfile> updateLibraryProfile(@PathVariable Long id, @RequestBody LibraryProfile libraryProfile) {
        LibraryProfile updatedProfile = libraryProfileService.updateLibraryProfile(id, libraryProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryProfile> getLibraryProfileById(@PathVariable Long id) {
        LibraryProfile libraryProfile = libraryProfileService.getLibraryProfileById(id);
        return ResponseEntity.ok(libraryProfile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LibraryProfile>> getAllLibraryProfiles() {
        List<LibraryProfile> libraryProfiles = libraryProfileService.getAllLibraryProfiles();
        return ResponseEntity.ok(libraryProfiles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibraryProfile(@PathVariable Long id) {
        libraryProfileService.deleteLibraryProfile(id);
        return ResponseEntity.ok("Library profile with ID " + id + " has been successfully deleted.");
    }

}
