package com.example.library.service.impl;


import com.example.library.dto.BookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.models.Book;
import com.example.library.repository.BookRepo;
import com.example.library.service.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll() {
        return bookMapper.toDtoList(bookRepo.findAll());
    }


    @Override
    public BookDto getById(Long id) {
        return bookMapper.toDto(bookRepo.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book saved = bookRepo.save(book);
        Book fullyLoaded = bookRepo.findById(saved.getId()).orElseThrow();
        return bookMapper.toDto(fullyLoaded);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        book.setName(bookDto.getNameDto());
        Book saved = bookRepo.save(book);
        return bookMapper.toDto(saved);
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
