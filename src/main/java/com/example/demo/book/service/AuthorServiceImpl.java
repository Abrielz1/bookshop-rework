package com.example.demo.book.service;

import com.example.demo.book.mapper.AuthorMapper;
import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import com.example.demo.book.model.entity.Author;
import com.example.demo.book.repository.AuthorRepository;
import com.example.demo.exception.exceptions.ObjectNotFoundException;
import com.example.demo.exception.exceptions.UnsupportedStateException;
import com.example.demo.user.model.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.demo.book.mapper.AuthorMapper.toAuthor;
import static com.example.demo.book.mapper.AuthorMapper.toAuthorDto;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final UserRepository userRepository;

    @Override
    public List<AuthorDto> getAuthorsList(PageRequest page) {

        log.info("\nAll authors list were sent via authors service at time: " + LocalDateTime.now() + "\n");
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toAuthorDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(Long authorId,
                                   Long userId) {

        User user = checkUserByIdInDataBase(userId);

        Author author = checkAuthorByIdInDataBase(authorId);

        if (!author.getUser().getId().equals(user.getId())) {
            log.info("Author requested by user %d who is not creator".formatted(user.getId()));
        }

        log.info("\nAuthor was sent with id: %d via authors service at time: ".formatted(authorId)
                + LocalDateTime.now() + "\n");
        return toAuthorDto(author);
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(Long userId,
                                  AuthorNewDto newAuthor) {

        User user = checkUserByIdInDataBase(userId);
        Author author = toAuthor(newAuthor);
        author.setUser(user);
        // todo: добавить список книг, которым не поставлен автор
        log.info(("\nAuthor with id: %d was created" +
                " via users by User with id: %d service at time: ")
                .formatted(author.getId(), user.getId()) + LocalDateTime.now() + "\n");
        return toAuthorDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public AuthorDto updateAuthorByUserIdAndAuthorId(Long authorId,
                                                     Long userId,
                                                     AuthorNewDto newAuthor) {

        User user = checkUserByIdInDataBase(userId);
        Author author = checkAuthorByIdInDataBase(authorId);

        if (!author.getUser().equals(user)) {
            log.info("Author requested by user %d who is not creator".formatted(user.getId()));
        }

        if (StringUtils.hasText(author.getFirstName())) {
            author.setFirstName(newAuthor.getFirstName());
        }

        if (StringUtils.hasText(author.getPatronym())) {
            author.setPatronym(newAuthor.getPatronym());
        }

        if (StringUtils.hasText(author.getLastName())) {
            author.setLastName(newAuthor.getLastName());
        }

        if (StringUtils.hasText(author.getBiography())) {
            author.setBiography(newAuthor.getBiography());
        }

        log.info(("\nAuthor with id: %d was updated" +
                " via users by User with id: %d service at time: ")
                .formatted(author.getId(), user.getId()) + LocalDateTime.now() + "\n");
        return toAuthorDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public AuthorDto deleteByAuthorIdAndUserId(Long authorId,
                                               Long userId) {

        User user = checkUserByIdInDataBase(userId);

        Author author = checkAuthorByIdInDataBase(authorId);

        if (!author.getUser().getId().equals(user.getId())) {
            log.info(("Author with id: %d requested on delete" +
                    " by user with id: %d who is not creator").formatted(authorId, user.getId()));
            throw new UnsupportedStateException("you not owner of author");
        }

        log.info(("\nAuthor was removed with id: %d" +
                " via authors service by user with id: %d at time: ").formatted(authorId, userId)
                + LocalDateTime.now() + "\n");

        authorRepository.findById(authorId)
                .ifPresent(authorRepository::delete);

        return toAuthorDto(author);
    }

    private User checkUserByIdInDataBase(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {

            log.error("No such element with id: %d!\n".formatted(userId));
            return new ObjectNotFoundException("User Account was not found!");
        });
    }

    private Author checkAuthorByIdInDataBase(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> {

            log.error("No such element with id: %d!\n".formatted(authorId));
            return new ObjectNotFoundException("Author was not found!");
        });
    }
}

