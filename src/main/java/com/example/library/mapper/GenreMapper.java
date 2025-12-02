package com.example.library.mapper;


import com.example.library.dto.GenreDto;
import com.example.library.models.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "nameDto", source = "name")
    GenreDto toDto(Genre genre);

    @Mapping(target = "name", source = "nameDto")
    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toDtoList(List<Genre> genres);
}