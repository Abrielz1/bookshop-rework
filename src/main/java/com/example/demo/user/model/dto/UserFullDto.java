package com.example.demo.user.model.dto;

import com.example.demo.book.model.entity.Author;
import com.example.demo.book.model.entity.Book;
import com.example.demo.book.model.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFullDto {

    private Long id;

    private String hash;

    private String username;

    private String email;

    private String displayName;

    private Long registrationTime; // todo: сконвертить в LDT

    private Long updateTime; // todo: сконвертить в LDT

    private List<Book> uploadedBooksList;

    private List<Author> createdAuthorsList;

    private List<Genre> createdGenreslist;
}