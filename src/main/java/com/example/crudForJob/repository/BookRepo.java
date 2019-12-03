package com.example.crudForJob.repository;

import com.example.crudForJob.model.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    Book findByBookName(String name);
}
