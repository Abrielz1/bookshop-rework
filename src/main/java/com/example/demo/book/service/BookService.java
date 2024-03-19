package com.example.demo.book.service;

import com.example.demo.book.model.dto.AuthorNewDto;
import com.example.demo.book.model.dto.BookDto;
import com.example.demo.book.model.dto.BookNewDto;

import java.util.List;

public interface BookService {

    List<BookDto> getBooksList();

    BookDto getBooksById(Long bookId,
                         Long userId);

    BookDto createBook(Long genreId,
                       Long userId,
                       BookNewDto newBook);

    BookDto updateBookByUserIdAndAuthorIdAndBookId(Long authorId,
                                                   Long bookId,
                                                   Long genreId,
                                                   Long userId,
                                                   BookNewDto updatedBook);

    BookDto deleteBookByBookIdAndUserId(Long bookId,
                                        Long userId);
}
