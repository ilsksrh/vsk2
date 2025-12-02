package com.example.library.service;


import com.example.library.dto.BookDto;

import java.util.List;

public interface BookService{

    List<BookDto> getAll();
    BookDto getById(Long id);
    BookDto addBook(BookDto bookDto);
    BookDto updateBook(Long id, BookDto bookDto);
    boolean deleteBook(Long id);
}
