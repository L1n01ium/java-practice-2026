package shop.user.infrastructure.persistence;

import shop.user.domain.User;
import shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/users";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1324";

    public UserRepositoryJdbcImpl() {
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String selectSQL = "SELECT id, name, email, password, profile_description FROM users";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_description")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
