package com.pshandy.rentservice.service;

import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.persistence.repository.RoleRepository;
import com.pshandy.rentservice.persistence.repository.UserRepository;
import com.pshandy.rentservice.security.AppUserDetails;
import com.pshandy.rentservice.web.dto.UserDto;
import com.pshandy.rentservice.web.error.UserAlreadyExistException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(
            @Autowired UserRepository userRepository,
            @Autowired RoleRepository roleRepository,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) {

        if (userRepository.existsByEmail(accountDto.getEmail())) {
            throw new UserAlreadyExistException("Аккаунт с таким именем уже существует: " + accountDto.getEmail());
        }

        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setMiddleName(accountDto.getMiddleName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER")));
        user.setPhoneNumber(accountDto.getPhoneNumber());

        return userRepository.save(user);

    }

    @Override
    public User updateUserAccount(User user, UserDto accountDto) {

        if (userRepository.existsByEmail(accountDto.getEmail())) {
            throw new UserAlreadyExistException("Аккаунт с таким именем уже существует: " + accountDto.getEmail());
        }

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setMiddleName(accountDto.getMiddleName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setPhoneNumber(accountDto.getPhoneNumber());

        return userRepository.save(user);

    }

    @Override
    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

}
