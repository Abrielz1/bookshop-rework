package com.example.demo.user.service;

import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface UserService {

    List<UserShortDto> getAllUsersAccountsList(PageRequest page);

    UserShortDto getUserAccountById(Long userId);

    UserShortDto registerUserAccount(UserNewDto newUser);

    UserShortDto updateUserAccount(Long userId, UserNewDto updateUser);

    UserShortDto removeUserAccountById(Long userId);
}
