package com.example.crudForJob.service;

import com.example.crudForJob.model.Dto.ADto;
import com.example.crudForJob.model.Dto.AuthorDto;
import com.example.crudForJob.model.Entity.Author;

import java.util.List;

public interface AuthorService {
    List<ADto> getAll();
    Author findByAuthorName(String name);
    Author saveAuthor(String authorName) throws Exception;
    AuthorDto findByIdAndBookName(Long id, String bookName);
}
