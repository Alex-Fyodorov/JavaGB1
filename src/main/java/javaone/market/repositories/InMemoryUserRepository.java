package javaone.market.repositories;

import javaone.market.exceptions.UserNotFoundException;
import javaone.market.models.Sex;
import javaone.market.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository{
    private final List<User> users;
    private static int count = 1;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
        addUser(new User("Tom", Sex.MALE, LocalDate.of(1984, 3, 19), "11111"));
        addUser(new User("Bob", Sex.MALE, LocalDate.of(1999, 11, 22), "22222"));
        addUser(new User("Jim", Sex.MALE, LocalDate.of(1972, 2, 29), "33333"));
        addUser(new User("John", Sex.MALE, LocalDate.of(1994, 8, 31), "44444"));
        addUser(new User("Sarah", Sex.FEMALE, LocalDate.of(2002, 5, 27), "55555"));
    }

    @Override
    public void addUser(User user) {
        user.setId(count++);
        users.add(user);
    }

    @Override
    public User findUserById(Integer userId) throws UserNotFoundException {
        return users.stream().filter(u -> u.getId().equals(userId)).findFirst()
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id: %d not found.", userId)));
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with username: %s not found.", username)));
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
