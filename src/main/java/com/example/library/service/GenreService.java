package com.example.library.service;

import com.example.library.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
    GenreDto getById(Long id);
    GenreDto addGenre(GenreDto genreDto);
    GenreDto updateGenre(Long id, GenreDto genreDto);
    boolean deleteGenre(Long id);
}
