package com.example.library.mapper;

import com.example.library.dto.GenreDto;
import com.example.library.models.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GenreMapperTest {

    @Autowired
    private GenreMapper genreMapper;

    @Test
    void convertEntityToDtoTest() {
        Genre genre = new Genre(1L, "Detective");

        GenreDto genreDto = genreMapper.toDto(genre);

        Assertions.assertNotNull(genreDto);
        Assertions.assertNotNull(genreDto.getId());
        Assertions.assertNotNull(genreDto.getNameDto());

        Assertions.assertEquals(genre.getId(), genreDto.getId());
        Assertions.assertEquals(genre.getName(), genreDto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        GenreDto genreDto = GenreDto.builder()
                .id(1L)
                .nameDto("Fantasy")
                .build();

        Genre genre = genreMapper.toEntity(genreDto);

        Assertions.assertNotNull(genre);
        Assertions.assertNotNull(genre.getId());
        Assertions.assertNotNull(genre.getName());

        Assertions.assertEquals(genreDto.getId(), genre.getId());
        Assertions.assertEquals(genreDto.getNameDto(), genre.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(1L, "Science Fiction"));
        genreList.add(new Genre(2L, "Romance"));
        genreList.add(new Genre(3L, "Thriller"));

        List<GenreDto> genreDtoList = genreMapper.toDtoList(genreList);

        Assertions.assertNotNull(genreDtoList);
        Assertions.assertNotEquals(0, genreDtoList.size());
        Assertions.assertEquals(genreList.size(), genreDtoList.size());

        for (int i = 0; i < genreList.size(); i++) {
            Genre genre = genreList.get(i);
            GenreDto dto = genreDtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());

            Assertions.assertEquals(genre.getId(), dto.getId());
            Assertions.assertEquals(genre.getName(), dto.getNameDto());
        }
    }
}