package com.example.library.repository;


import com.example.library.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BookRepo extends JpaRepository<Book, Long> {
}
