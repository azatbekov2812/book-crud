package com.example.crudForJob.model.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExceptionDto {
    private String error;
    private String bookexcep;

    public ExceptionDto(String error) {
        this.error = error;
    }
}
