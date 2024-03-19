package com.example.demo.book.service;

import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAuthorsList(PageRequest page);

    AuthorDto getAuthorById(Long authorId, Long userId);

    AuthorDto createAuthor(Long userId, AuthorNewDto newAuthor);

    AuthorDto updateAuthorByUserIdAndAuthorId(Long authorId, Long userId, AuthorNewDto newAuthor);

    AuthorDto deleteByAuthorIdAndUserId(Long authorId, Long userId);
}
