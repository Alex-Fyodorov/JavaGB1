package javaone.market.repositories;

import javaone.market.exceptions.UserNotFoundException;
import javaone.market.models.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User findUserById(Integer userId) throws UserNotFoundException;
    User findUserByUsername(String username) throws UserNotFoundException;
    List<User> getAll();
}
