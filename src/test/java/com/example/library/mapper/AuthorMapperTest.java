package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.models.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void convertEntityToDtoTest() {
        Author author = new Author(1L, "Lev Tolstoy");

        AuthorDto authorDto = authorMapper.toDto(author);

        Assertions.assertNotNull(authorDto);
        Assertions.assertNotNull(authorDto.getId());
        Assertions.assertNotNull(authorDto.getNameDto());

        Assertions.assertEquals(author.getId(), authorDto.getId());
        Assertions.assertEquals(author.getName(), authorDto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto authorDto = AuthorDto.builder()
                .id(1L)
                .nameDto("Anna Mari")
                .build();

        Author author = authorMapper.toEntity(authorDto);

        Assertions.assertNotNull(author);
        Assertions.assertNotNull(author.getId());
        Assertions.assertNotNull(author.getName());

        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getNameDto(), author.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1L, "Anton Chekhov"));
        authorList.add(new Author(2L, "Nikolai Gogol"));
        authorList.add(new Author(3L, "Alexander Pushkin"));

        List<AuthorDto> authorDtoList = authorMapper.toDtoList(authorList);

        Assertions.assertNotNull(authorDtoList);
        Assertions.assertNotEquals(0, authorDtoList.size());
        Assertions.assertEquals(authorList.size(), authorDtoList.size());

        for (int i = 0; i < authorList.size(); i++) {
            Author author = authorList.get(i);
            AuthorDto dto = authorDtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());

            Assertions.assertEquals(author.getId(), dto.getId());
            Assertions.assertEquals(author.getName(), dto.getNameDto());
        }
    }
}