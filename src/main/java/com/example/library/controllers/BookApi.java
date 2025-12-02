package com.example.library.controllers;

import com.example.library.dto.BookDto;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookApi {

    @Autowired
    private BookService bookService;

    @GetMapping("/admin")

    public List<BookDto> getAllForAdmin() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        BookDto added = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book was added to the library! (ID: " + added.getId() + ")");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto updated = bookService.updateBook(id, bookDto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
