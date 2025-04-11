package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import edu.unimagdalena.entities.User;
import edu.unimagdalena.exceptions.UserNotFoundException;
import edu.unimagdalena.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   
   public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
   }
   @Override
    public User create(User user){
    User copy = new User(user.getId(),
                         user.getFullname(),
                         user.getUsername(),
                         user.getPassword());
        return userRepository.save(copy);
   }
   @Override
    public Optional<User> update(Integer id, User newUser) {
    return userRepository.findById(id)
    .map(oldUser -> {
        User user = oldUser.updateWith(newUser);
        return userRepository.save(user);
      });
    }
   @Override
    public List<User> findAll() {
    return userRepository.findAll();    
    }
   @Override
    public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
    }
   @Override
    public void delete(Integer id) {
    userRepository.deleteById(id);
    }
   @Override
    public User getUserById(int userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
        return optionalUser.get();
    } else {
        throw new UserNotFoundException();
    }
    }
}

