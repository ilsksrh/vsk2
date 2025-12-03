package com.example.library.service;

import com.example.library.dto.BookDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getAllTest() {
        List<BookDto> dtos = bookService.getAll();
        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (BookDto dto : dtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int index = random.nextInt(bookService.getAll().size());
        Long id = bookService.getAll().get(index).getId();

        BookDto dto = bookService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getNameDto());

        BookDto check = bookService.getById(-1L);
        Assertions.assertNull(check);
    }

    @Test
    void addTest() {
        BookDto dto = new BookDto();
        dto.setNameDto("Sansara");

        BookDto added = bookService.addBook(dto);

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getNameDto());

        BookDto fromDb = bookService.getById(added.getId());
        Assertions.assertNotNull(fromDb);

        Assertions.assertEquals(added.getId(), fromDb.getId());
        Assertions.assertEquals(added.getNameDto(), fromDb.getNameDto());
    }

    @Test
    void updateTest() {
        List<BookDto> all = bookService.getAll();
        if (all.isEmpty()) return;

        Random random = new Random();
        int index = random.nextInt(all.size());
        Long id = all.get(index).getId();

        BookDto updateDto = BookDto.builder()
                .id(id)
                .nameDto("Sansara")
                .build();

        BookDto updated = bookService.updateBook(id, updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertEquals("Sansara", updated.getNameDto());
        Assertions.assertEquals(id, updated.getId());

        BookDto fromDb = bookService.getById(id);
        Assertions.assertNotNull(fromDb);
        Assertions.assertEquals("Sansara", fromDb.getNameDto());
        Assertions.assertEquals(updated.getId(), fromDb.getId());
    }

    @Test
    void deleteTest() {
        List<BookDto> all = bookService.getAll();
        if (all.isEmpty()) return;

        Random random = new Random();
        int index = random.nextInt(all.size());
        Long id = all.get(index).getId();

        bookService.deleteBook(id);

        BookDto deleted = bookService.getById(id);
        Assertions.assertNull(deleted);
    }
}