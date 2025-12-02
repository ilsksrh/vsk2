package com.example.library.mapper;


import com.example.library.dto.AuthorDto;
import com.example.library.models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "nameDto", source = "name")
    AuthorDto toDto(Author author);

    @Mapping(target = "name", source = "nameDto")
    Author toEntity(AuthorDto authorDto);

    List<AuthorDto> toDtoList(List<Author> authors);

}