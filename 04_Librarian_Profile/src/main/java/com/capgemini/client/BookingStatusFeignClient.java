package com.capgemini.client;

import com.capgemini.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="BOOKING-STATUS", url="http://localhost:8084")
public interface BookingStatusFeignClient {

    @GetMapping("/api/books/status/{status}")
    public ResponseEntity<List<Book>> findBooksByStatus(@PathVariable String status);


    @PutMapping("/api/{id}/status")
    public ResponseEntity<Book> updateBookStatus(@PathVariable Long id, @RequestParam String status);
}
