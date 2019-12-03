package com.example.crudForJob.repository;

import com.example.crudForJob.model.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByAuthorName(String name);
}
