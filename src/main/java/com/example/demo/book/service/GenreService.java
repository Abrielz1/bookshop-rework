package com.example.demo.book.service;

import com.example.demo.book.model.dto.GenreDto;
import com.example.demo.book.model.dto.GenreNewDto;
import java.util.List;

public interface GenreService {

    List<GenreDto> getGenreList();

    GenreDto getBooksById(Long genreId,
                          Long userId);

    GenreDto createBook(Long userId,
                        GenreNewDto newGenre);

    GenreDto updateGenreByUserIdAndGenreId(Long genreId,
                                           Long userId,
                                           GenreNewDto updateGenre);

    GenreDto deleteGenreByGenreIdAndUserId(Long genreId,
                                           Long userId);
}
