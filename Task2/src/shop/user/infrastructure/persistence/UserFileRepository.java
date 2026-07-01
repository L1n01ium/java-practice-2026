package shop.user.infrastructure.persistence;

import shop.user.domain.User;
import shop.user.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    User user = userMapper.fromLine(line);
                    if (user.getEmail().equals(email)) {
                        return Optional.of(user);
                    }
                }
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public Optional<User> findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] splitStr = line.split("\\|");
                    if (splitStr.length == 4) {
                        if (splitStr[0].equals(id)) {
                            return Optional.of(new User(splitStr[0], splitStr[1], splitStr[2], splitStr[3]));
                        }
                    }
                }
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    User exist = userMapper.fromLine(line);
                    if (exist.getId().equals(user.getId())) {
                        lines.add(userMapper.toLine(user));
                    } else {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line: lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}