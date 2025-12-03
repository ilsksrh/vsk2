package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.dto.GenreDto;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDtoTest() {
        Author author = new Author(1L, "J.K. Rowling");
        List<Genre> genres = List.of(
                new Genre(1L, "Fantasy"),
                new Genre(2L, "Adventure")
        );
        Book book = new Book(1L, "Harry Potter", author, genres);

        BookDto bookDto = bookMapper.toDto(book);

        Assertions.assertNotNull(bookDto);
        Assertions.assertNotNull(bookDto.getId());
        Assertions.assertNotNull(bookDto.getNameDto());
        Assertions.assertNotNull(bookDto.getAuthor());
        Assertions.assertNotNull(bookDto.getGenres());

        Assertions.assertEquals(book.getId(), bookDto.getId());
        Assertions.assertEquals(book.getName(), bookDto.getNameDto());
        Assertions.assertEquals(book.getAuthor().getId(), bookDto.getAuthor().getId());
        Assertions.assertEquals(book.getAuthor().getName(), bookDto.getAuthor().getNameDto());
        Assertions.assertEquals(2, bookDto.getGenres().size());
        Assertions.assertEquals("Fantasy", bookDto.getGenres().get(0).getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto authorDto = AuthorDto.builder().id(1L).nameDto("George Orwell").build();
        List<GenreDto> genreDtos = List.of(
                GenreDto.builder().id(1L).nameDto("Dystopia").build(),
                GenreDto.builder().id(2L).nameDto("Political").build()
        );

        BookDto bookDto = BookDto.builder()
                .id(1L)
                .nameDto("1984")
                .author(authorDto)
                .genres(genreDtos)
                .build();

        Book book = bookMapper.toEntity(bookDto);

        Assertions.assertNotNull(book);
        Assertions.assertNotNull(book.getId());
        Assertions.assertNotNull(book.getName());
        Assertions.assertNotNull(book.getAuthor());
        Assertions.assertNotNull(book.getGenres());

        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getNameDto(), book.getName());
        Assertions.assertEquals(bookDto.getAuthor().getId(), book.getAuthor().getId());
        Assertions.assertEquals(bookDto.getAuthor().getNameDto(), book.getAuthor().getName());
        Assertions.assertEquals(2, book.getGenres().size());
        Assertions.assertEquals("Dystopia", book.getGenres().get(0).getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Book> bookList = new ArrayList<>();
        Author author1 = new Author(1L, "Author One");
        Author author2 = new Author(2L, "Author Two");

        bookList.add(new Book(1L, "Book One", author1, List.of(new Genre(1L, "Genre A"))));
        bookList.add(new Book(2L, "Book Two", author2, List.of(new Genre(2L, "Genre B"), new Genre(3L, "Genre C"))));

        List<BookDto> bookDtoList = bookMapper.toDtoList(bookList);

        Assertions.assertNotNull(bookDtoList);
        Assertions.assertNotEquals(0, bookDtoList.size());
        Assertions.assertEquals(bookList.size(), bookDtoList.size());

        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            BookDto dto = bookDtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getAuthor());
            Assertions.assertNotNull(dto.getGenres());

            Assertions.assertEquals(book.getId(), dto.getId());
            Assertions.assertEquals(book.getName(), dto.getNameDto());
            Assertions.assertEquals(book.getAuthor().getName(), dto.getAuthor().getNameDto());
        }
    }
}