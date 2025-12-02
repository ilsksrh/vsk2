package com.example.library.service.impl;

import com.example.library.dto.GenreDto;
import com.example.library.mapper.GenreMapper;
import com.example.library.models.Genre;
import com.example.library.repository.GenreRepo;
import com.example.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepo genreRepo;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAll(){
        return genreMapper.toDtoList(genreRepo.findAll());
    }
    @Override
    public GenreDto getById(Long id){
        return genreMapper.toDto(genreRepo.findById(id).orElse(null));
    }
    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        Genre saved = genreRepo.save(genre);
        return genreMapper.toDto(saved);
    }

    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre genre = genreRepo.findById(id).orElse(null);
        if (genre == null) return null;
        genre.setName(genreDto.getNameDto());
        Genre saved = genreRepo.save(genre);
        return genreMapper.toDto(saved);
    }
    @Override
    public boolean deleteGenre(Long id){
        if(genreRepo.existsById(id)){
            genreRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
