package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    @Mapping(target = "nameDto", source = "name")
    BookDto toDto(Book book);

    @Mapping(target = "name", source = "nameDto")
    Book toEntity(BookDto bookDto);

    List<BookDto> toDtoList(List<Book> books);
}

