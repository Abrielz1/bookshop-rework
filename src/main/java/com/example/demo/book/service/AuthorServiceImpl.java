package com.example.demo.book.service;

import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import com.example.demo.book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    // todo: finAll
    @Override
    public List<AuthorDto> getAuthorsList() {

        return null;
    }

    // todo: findById
    @Override
    public AuthorDto getAuthorById(Long authorId, Long userId) {

        return null;
    }

    // todo: create
    @Override
    @Transactional
    public AuthorDto createAuthor(AuthorNewDto newAuthor) {

        return null;
    }

    // todo: update
    @Override
    @Transactional
    public AuthorDto updateAuthorByUserIdAndAuthorId(Long authorId, Long userId, AuthorNewDto newAuthor) {

        return null;
    }
    // todo: deleteById
    @Override
    @Transactional
    public AuthorDto deleteByAuthorIdAndUserId(Long authorId, Long userId) {

        return null;
    }
}
