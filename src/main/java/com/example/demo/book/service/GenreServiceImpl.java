package com.example.demo.book.service;

import com.example.demo.book.model.dto.GenreDto;
import com.example.demo.book.model.dto.GenreNewDto;
import com.example.demo.book.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    // todo: finAll
    @Override
    public List<GenreDto> getGenreList() {

        return null;
    }


    // todo: findById

    @Override
    public GenreDto getBooksById(Long genreId,
                                 Long userId) {

        return null;
    }

    // todo: create
    @Override
    @Transactional
    public GenreDto createBook(Long userId,
                               GenreNewDto newGenre) {

        return null;
    }

    // todo: update
    @Override
    @Transactional
    public GenreDto updateGenreByUserIdAndGenreId(Long genreId,
                                                  Long userId,
                                                  GenreNewDto updateGenre) {

        return null;
    }

    // todo: deleteById
    @Override
    @Transactional
    public GenreDto deleteGenreByGenreIdAndUserId(Long genreId,
                                                  Long userId) {

        return null;
    }

}
