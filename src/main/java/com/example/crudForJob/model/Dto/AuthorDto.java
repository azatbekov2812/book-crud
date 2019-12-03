package com.example.crudForJob.model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AuthorDto {
    private Long id;
    private String authorName;
    private BookDto bookDto; // не должен быть
}
