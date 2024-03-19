package com.example.demo.user.service;

import com.example.demo.exception.exceptions.ObjectNotFoundException;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.model.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import static com.example.demo.user.mapper.UserMapper.toUserShortDto;
import static com.example.demo.user.mapper.UserMapper.toUser;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

        @Override
    public List<UserShortDto> getAllUsersAccountsList(PageRequest page) {

        log.info("\nAll user Accounts were sent via users service at time: " + LocalDateTime.now() +"\n");
        return userRepository.findAll(page)
                .stream()
                .map(UserMapper::toUserShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserShortDto getUserAccountById(Long userId) {

        log.info("\nUser Account was sent with id: %d via users service at time: ".formatted(userId)
                + LocalDateTime.now() +"\n");
        return toUserShortDto(checkUserInDataBase(userId));
    }


    @Override
    @Transactional
    public UserShortDto registerUserAccount(UserNewDto newUser) {

        log.info("\nUser account was created via users service at time: " + LocalDateTime.now() + "\n");
        return toUserShortDto(userRepository.save(toUser(newUser)));
    }

    @Override
    @Transactional
    public UserShortDto updateUserAccount(Long userId, UserNewDto updateUser) {

        User userFromDBtoUpdate = checkUserInDataBase(userId);

        if (StringUtils.hasText(updateUser.getUsername())) {
            userFromDBtoUpdate.setUsername(updateUser.getUsername());
        }

        if (StringUtils.hasText(updateUser.getEmail())) {
            userFromDBtoUpdate.setUsername(updateUser.getEmail());
        }

        if (StringUtils.hasText(updateUser.getPassword())) {
            userFromDBtoUpdate.setPassword(updateUser.getPassword());
        }

        if (StringUtils.hasText(updateUser.getDisplayName())) {
            userFromDBtoUpdate.setDisplayName(updateUser.getDisplayName());
        }

        userFromDBtoUpdate.setUpdateTime(System.currentTimeMillis());

        log.info("\nUser account with id: %d was updated via users service at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");

        return toUserShortDto(userRepository.save(userFromDBtoUpdate));
    }


    @Override
    @Transactional
    public UserShortDto removeUserAccountById(Long userId) {

        User user = checkUserInDataBase(userId);

        userRepository.findById(userId).ifPresent(userRepository::delete);
        log.info("\nUser account with id: %d was deleted via users service at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");

        return toUserShortDto(user);
    }

    private User checkUserInDataBase(Long userId) {
       return userRepository.findById(userId).orElseThrow(() -> {

            log.error("No such element with id: %d!\n".formatted(userId));
            return new ObjectNotFoundException("User Account was not found!");
        });
    }
}
