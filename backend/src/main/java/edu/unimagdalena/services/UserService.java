package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.entities.User;
import edu.unimagdalena.exceptions.DuplicateCodigoException;
import edu.unimagdalena.exceptions.UserNotFoundException;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con {@link User}.
 * Proporciona métodos CRUD estándar y operaciones específicas para la entidad User.
 */
public interface UserService {

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user Objeto User con los datos del nuevo usuario.
     * @return El usuario creado con ID asignado.
     * @throws IllegalArgumentException si el usuario es nulo o contiene datos inválidos.
     * @throws DuplicateCodigoException si ya existe un usuario con el mismo identificador único.
     */
    User create(User user);

    /**
     * Actualiza los datos de un usuario existente.
     * @param id Identificador único del usuario a actualizar (debe ser mayor a 0).
     * @param newUser Objeto User con los nuevos datos (no debe ser nulo).
     * @return {@link Optional} conteniendo el usuario actualizado si existe, o vacío si no se encontró.
     * @throws IllegalArgumentException si newUser es nulo o el ID es inválido.
     */
    Optional<User> update(Integer id, User newUser);

     /**
     * Obtiene todos los usuarios registrados en el sistema.
     * @return Lista completa de usuarios (lista vacía si no hay registros).
     */
    List<User> findAll();

    /**
     * Busca un usuario por su ID único. 
     * @param id Identificador del usuario.
     * @return {@link Optional} conteniendo el usuario si existe, o vacío si no se encuentra.
     */
    Optional<User> findById(Integer id);

    /**
     * Elimina un usuario existente por su ID. 
     * @param id Identificador único del usuario a eliminar.
     * @throws UserNotFoundException si no existe un usuario con el ID proporcionado.
     */
    void delete(Integer id);

    /**
     * Obtiene un usuario por su ID (alternativa a findById con manejo de excepciones).
     * @param userId Identificador único del usuario (debe ser mayor a 0).
     * @return El usuario encontrado.
     * @throws UserNotFoundException si no existe un usuario con el ID proporcionado.
     * @throws IllegalArgumentException si el ID es inválido.
     */
    User getUserById(int userId); 
    
}
