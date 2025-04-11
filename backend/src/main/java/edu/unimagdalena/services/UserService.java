package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.entities.User;

public interface UserService {
    User create(User user);
    Optional<User> update(Integer id, User newUser);
    List<User> findAll();
    Optional<User> findById(Integer id);
    void delete(Integer id);
    User getUserById(int userId); 
}
