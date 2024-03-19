package com.example.demo.user.service;

import com.example.demo.exception.exceptions.ObjectNotFoundException;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.model.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl service;

    UserNewDto userNewDto;

    private User user;

    private UserShortDto userShortDto;

    @BeforeEach
    void beforeEach() {

        userNewDto = UserNewDto.builder()
                .username("User1 name")
                .email("user1@mail.com")
                .password("12345")
                .displayName("name User1")
                .build();

        user = User.builder()
                .id(1L)
                .hash(UUID.randomUUID().toString())
                .username("User1 name")
                .email("user1@mail.com")
                .password("12345")
                .displayName("name User1")
                .registrationTime(System.currentTimeMillis())
                .updateTime(System.currentTimeMillis())
                .createdAuthorsList(new ArrayList<>())
                .uploadedBooksList(new ArrayList<>())
                .createdGenresList(new ArrayList<>())
                .build();

        userRepository.save(user);

       userShortDto = UserMapper.toUserShortDto(user);
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void getAllUsersAccountsList() {

        List<User> usersList = new ArrayList<>();
        usersList.add(user);
        PageRequest request = PageRequest.of(0, 20);

        when(userRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(usersList));

        List<UserShortDto> usersShortDtoList = this.service.getAllUsersAccountsList(request);

        assertEquals(1, usersShortDtoList.size());
        assertEquals(userShortDto, usersShortDtoList.get(0));
        assertEquals(userShortDto.getDisplayName(), usersShortDtoList.get(0).getDisplayName());
        assertEquals(userShortDto.getEmail(), usersShortDtoList.get(0).getEmail());

        assertThat(1).isEqualTo(usersShortDtoList.size());
        assertThat(userShortDto).isEqualTo(usersShortDtoList.get(0));
        assertThat(userShortDto.getDisplayName()).isEqualTo(usersShortDtoList.get(0).getDisplayName());
        assertThat(userShortDto.getEmail()).isEqualTo(usersShortDtoList.get(0).getEmail());
    }

    @Test
    void getUserAccountById() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(user));

        UserShortDto userShortDtoFromDb = service.getUserAccountById(user.getId());

        assertThat(userShortDtoFromDb.getDisplayName()).isEqualTo(user.getDisplayName());
        assertThat(userShortDtoFromDb.getEmail()).isEqualTo(user.getEmail());
    }

   @Test
   void getUserWrongIdTest() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> service.getUserAccountById(100L));

        assertEquals("User Account was not found!", exception.getMessage());
   }

        @Test
    void getAllUsersWhenUserFoundThenUserNotFoundExceptionThrown() {

        Long userId = 0L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> service.getUserAccountById(userId));
    }

    @Test
    void registerUserAccount() {

        when(userRepository.save(any(User.class))).thenReturn(user);
               UserShortDto userDto1 = service.registerUserAccount(userNewDto);

        assertEquals(user.getDisplayName(), userDto1.getDisplayName());
        assertEquals(user.getEmail(), userDto1.getEmail());
    }

    @Test
    void updateUserAccount() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional
                .ofNullable(user));

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        userNewDto.setDisplayName("User0 name");
        userNewDto.setEmail("user1@gmail.com");

        UserShortDto userShortDtoFromDB = service.updateUserAccount(user.getId(), userNewDto);

        assertEquals("User0 name", userShortDtoFromDB.getDisplayName());
        assertEquals("user1@gmail.com", userShortDtoFromDB.getEmail());
    }

    @Test
    void removeUserAccountById() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional
                        .ofNullable(user));

        UserShortDto userShortDtoFromDB = service.removeUserAccountById(user.getId());

        assertEquals("name User1", userShortDtoFromDB.getDisplayName());
        assertEquals("user1@mail.com", userShortDtoFromDB.getEmail());
    }

    @Test
    void updateUserWithNoUser() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ObjectNotFoundException exc = assertThrows(ObjectNotFoundException.class,
                () -> service.updateUserAccount(10000L, userNewDto)
        );

        assertEquals("User Account was not found!", exc.getMessage());
    }

    @Test
    void deleteUserTestWithNoUser() {

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ObjectNotFoundException exc = assertThrows(ObjectNotFoundException.class,
                () -> service.removeUserAccountById(10000L)
        );

        assertEquals("User Account was not found!", exc.getMessage());
    }
}