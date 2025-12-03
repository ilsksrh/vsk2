package com.example.library.service;


import com.example.library.dto.GenreDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    void getAllTest() {
        List<GenreDto> genres = genreService.getAll();
        Assertions.assertNotNull(genres);
        Assertions.assertNotEquals(0, genres.size());

        for (GenreDto g : genres) {
            Assertions.assertNotNull(g);
            Assertions.assertNotNull(g.getId());
            Assertions.assertNotNull(g.getNameDto());
        }
    }

    @Test
    void getByIdTest() {
        List<GenreDto> all = genreService.getAll();
        if (all.isEmpty()) return;

        Long id = all.get(new Random().nextInt(all.size())).getId();
        GenreDto genre = genreService.getById(id);

        Assertions.assertNotNull(genre);
        Assertions.assertNotNull(genre.getId());
        Assertions.assertNotNull(genre.getNameDto());

        Assertions.assertNull(genreService.getById(-1L));
    }

    @Test
    void addTest() {
        GenreDto dto = GenreDto.builder()
                .nameDto("Fantasy")
                .build();

        GenreDto added = genreService.addGenre(dto);

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertEquals("Fantasy", added.getNameDto());

        GenreDto fromDb = genreService.getById(added.getId());
        Assertions.assertEquals(added.getId(), fromDb.getId());
        Assertions.assertEquals("Fantasy", fromDb.getNameDto());
    }

    @Test
    void updateTest() {
        List<GenreDto> all = genreService.getAll();
        if (all.isEmpty()) return;

        Long id = all.get(new Random().nextInt(all.size())).getId();

        GenreDto updateDto = GenreDto.builder()
                .id(id)
                .nameDto("SciFi Updated")
                .build();

        GenreDto updated = genreService.updateGenre(id, updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(id, updated.getId());
        Assertions.assertEquals("SciFi Updated", updated.getNameDto());

        GenreDto fromDb = genreService.getById(id);
        Assertions.assertEquals("SciFi Updated", fromDb.getNameDto());
    }

    @Test
    void deleteTest() {
        List<GenreDto> all = genreService.getAll();
        if (all.isEmpty()) return;

        Long id = all.get(new Random().nextInt(all.size())).getId();

        Assertions.assertTrue(genreService.deleteGenre(id));
        Assertions.assertNull(genreService.getById(id));
    }
}