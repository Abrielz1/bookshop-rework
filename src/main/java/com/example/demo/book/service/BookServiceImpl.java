package com.example.demo.book.service;

import com.example.demo.book.model.dto.BookDto;
import com.example.demo.book.model.dto.BookNewDto;
import com.example.demo.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // todo: finAll
    @Override
    public List<BookDto> getBooksList() {

        return null;
    }

    // todo: findById
    @Override
    public BookDto getBooksById(Long bookId, Long userId) {

        return null;
    }

    // todo: create
    @Override
    @Transactional
    public BookDto createBook(Long genreId,
                              Long userId,
                              BookNewDto newBook) {

        return null;
    }

    // todo: update
    @Override
    @Transactional
    public BookDto updateBookByUserIdAndAuthorIdAndBookId(Long authorId,
                                                          Long bookId,
                                                          Long genreId,
                                                          Long userId,
                                                          BookNewDto updatedBook) {

        return null;
    }

    // todo: deleteById
    @Override
    @Transactional
    public BookDto deleteBookByBookIdAndUserId(Long bookId, Long userId) {

        return null;
    }
}
