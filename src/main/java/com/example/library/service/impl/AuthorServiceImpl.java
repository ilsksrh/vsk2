package com.example.library.service.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.mapper.AuthorMapper;
import com.example.library.models.Author;
import com.example.library.repository.AuthorRepo;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll() {
        return authorMapper.toDtoList(authorRepo.findAll());
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorMapper.toDto(authorRepo.findById(id).orElse(null));
    }

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        Author author = authorMapper.toEntity(authorDto);
        authorRepo.save(author);
        return authorMapper.toDto(author);
    }


    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author author = authorRepo.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        author.setName(authorDto.getNameDto());
        Author saved = authorRepo.save(author);
        return authorMapper.toDto(saved);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepo.deleteById(id);
    }
}
