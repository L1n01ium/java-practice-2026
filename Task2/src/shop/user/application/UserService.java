package shop.user.application;

import shop.user.domain.User;
import shop.user.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, String profileDescription) {

    }

    public User findById(String id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public boolean updateProfile(String email, String newProfileDescription) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setProfileDescription(newProfileDescription);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else {

            return false;
        }
    }
}
