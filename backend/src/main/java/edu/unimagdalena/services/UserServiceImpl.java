package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import edu.unimagdalena.entities.User;
import edu.unimagdalena.exceptions.UserNotFoundException;
import edu.unimagdalena.repositories.UserRepository;

/**
 * Implementación concreta de {@link UserService} que gestiona operaciones de usuarios.
 * Delega la persistencia a {@link UserRepository} y añade lógica de negocio específica.
 *
 * <p>Sigue el patrón de inyección de dependencias mediante constructor.</p>
 */
@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   
   /**
     * Constructor para inyección de dependencias.
     * @param userRepository Repositorio de usuarios (no debe ser nulo).
     * @throws IllegalArgumentException si userRepository es nulo.
     */
   public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
   }

   /**
     * {@inheritDoc}
     * @implNote Crea una copia defensiva del usuario para evitar modificación accidental de parámetros.
     * @throws IllegalArgumentException si el usuario es nulo.
     */
   @Override
    public User create(User user){
    User copy = new User(user.getId(),
                         user.getFullname(),
                         user.getUsername(),
                         user.getPassword());
        return userRepository.save(copy);
   }

   /**
     * {@inheritDoc}
     * @implNote Realiza actualización parcial mediante el patrón builder en {@link User#updateWith(User)}.
     * @throws IllegalArgumentException si newUser es nulo.
     */
   @Override
    public Optional<User> update(Integer id, User newUser) {
    return userRepository.findById(id)
    .map(oldUser -> {
        User user = oldUser.updateWith(newUser);
        return userRepository.save(user);
      });
    }

     /**
     * {@inheritDoc}
     * @implNote Para grandes volúmenes de usuarios, considerar implementar paginación.
     */
   @Override
    public List<User> findAll() {
    return userRepository.findAll();    
    }
    
    /**
     * {@inheritDoc}
     * @implNote El Optional permite manejo explícito de casos "no encontrados".
     */
   @Override
    public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     * @implNote No lanza excepción si el ID no existe (comportamiento de Spring Data JPA).
     */
   @Override
    public void delete(Integer id) {
    userRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     * @throws UserNotFoundException si no existe usuario con el ID proporcionado.
     * @throws IllegalArgumentException si userId ≤ 0.
     * @apiNote Preferir este método sobre findById() cuando se requiera manejo explícito de errores.
     */
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

