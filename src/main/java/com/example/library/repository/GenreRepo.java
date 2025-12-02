package com.example.library.repository;


import com.example.library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {
}
