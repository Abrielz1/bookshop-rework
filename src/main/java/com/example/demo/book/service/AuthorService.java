package com.example.demo.book.service;

import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAuthorsList();

    AuthorDto getAuthorById(Long authorId, Long userId);

    AuthorDto createAuthor(AuthorNewDto newAuthor);

    AuthorDto updateAuthorByUserIdAndAuthorId(Long authorId, Long userId, AuthorNewDto newAuthor);

    AuthorDto deleteByAuthorIdAndUserId(Long authorId, Long userId);
}
