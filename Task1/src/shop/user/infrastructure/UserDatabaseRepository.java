package shop.user.infrastructure;

import shop.user.domain.User;
import shop.user.repository.UserRepository;

public class UserDatabaseRepository implements UserRepository {

    @Override
    public void save(User user) {
        System.out.println("Сохраняем в базу данных...");
    }

    @Override
    public User findById(String id) {
        System.out.println("Получаем из базы данных");
        return null;
    }
}
