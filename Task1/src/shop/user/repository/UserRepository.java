package shop.user.repository;

import shop.user.domain.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
