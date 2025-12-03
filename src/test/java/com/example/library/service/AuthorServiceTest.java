package com.example.library.service;

import com.example.library.dto.AuthorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void getAllTest() {
        List<AuthorDto> dtos = authorService.getAll();
        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (AuthorDto dto : dtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int index = random.nextInt(authorService.getAll().size());
        Long id = authorService.getAll().get(index).getId();

        AuthorDto dto = authorService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getNameDto());

        AuthorDto check = authorService.getById(-1L);
        Assertions.assertNull(check);
    }

    @Test
    void addTest() {
        AuthorDto dto = new AuthorDto();
        dto.setNameDto("Sansara");

        AuthorDto added = authorService.addAuthor(dto);

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getNameDto());

        AuthorDto fromDb = authorService.getById(added.getId());
        Assertions.assertNotNull(fromDb);

        Assertions.assertEquals(added.getId(), fromDb.getId());
        Assertions.assertEquals(added.getNameDto(), fromDb.getNameDto());
    }

    @Test
    void updateTest() {
        List<AuthorDto> all = authorService.getAll();
        if (all.isEmpty()) return;

        Random random = new Random();
        int index = random.nextInt(all.size());
        Long id = all.get(index).getId();

        AuthorDto updateDto = AuthorDto.builder()
                .id(id)
                .nameDto("Sansara")
                .build();

        AuthorDto updated = authorService.updateAuthor(id, updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertEquals("Sansara", updated.getNameDto());
        Assertions.assertEquals(id, updated.getId());

        AuthorDto fromDb = authorService.getById(id);
        Assertions.assertNotNull(fromDb);
        Assertions.assertEquals("Sansara", fromDb.getNameDto());
        Assertions.assertEquals(updated.getId(), fromDb.getId());
    }

    @Test
    void deleteTest() {
        List<AuthorDto> all = authorService.getAll();
        if (all.isEmpty()) return;

        Random random = new Random();
        int index = random.nextInt(all.size());
        Long id = all.get(index).getId();

        authorService.deleteAuthor(id);

        AuthorDto deleted = authorService.getById(id);
        Assertions.assertNull(deleted);
    }
}