package com.example.library.service;


import com.example.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();
    AuthorDto getById(Long id);
    AuthorDto addAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
    void deleteAuthor(Long id);

}
