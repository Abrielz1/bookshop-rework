package com.example.demo.book.mapper;

import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import com.example.demo.book.model.entity.Author;

public class AuthorMapper {

    public static Author toAuthor(AuthorNewDto newAuthor) {
        return Author.builder()
                .id(null)
                .firstName(newAuthor.getFirstName())
                .patronym(newAuthor.getPatronym())
                .lastName(newAuthor.getLastName())
                .biography(newAuthor.getBiography())
                .build();
    }

    public static AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .patronym(author.getPatronym())
                .lastName(author.getLastName())
                .biography(author.getBiography())
                .build();
    }
}
