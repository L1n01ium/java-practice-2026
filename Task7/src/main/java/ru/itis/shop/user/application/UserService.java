package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public UserDto findUserDtoById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(UserDto::fromUser).orElse(null);
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllUsersByProfileDescription(String description) {
        return userRepository.findAllByProfileDescription(description).stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    public boolean updateProfileByEmail(String email, String newDescription) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfileDescription(newDescription);
            userRepository.update(user);
            return true;
        }
        return false;
    }
}
