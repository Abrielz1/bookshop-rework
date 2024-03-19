package com.example.demo.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorNewDto {

    private String firstName;

    private String patronym;

    private String lastName;

    private String biography;

    // todo: лист книг, если их добавили до автора
}
