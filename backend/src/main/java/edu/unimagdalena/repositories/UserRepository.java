package edu.unimagdalena.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.unimagdalena.entities.User;

/**
 * Repositorio para operaciones de base de datos relacionadas con la entidad {@link User}.
 * Proporciona métodos para acceder y gestionar usuarios en el sistema.
 * <p>Extiende {@link JpaRepository} heredando operaciones CRUD básicas y de paginación.</p>
 */
public interface UserRepository extends JpaRepository<User, Integer> {
/**
     * Busca un usuario por su ID único.
     * @param id El identificador único del usuario
     * @return Un {@link Optional} conteniendo el usuario si existe, o vacío si no se encuentra
     * @throws IllegalArgumentException si el ID proporcionado es nulo
     * @implNote Este método sobrescribe el método predeterminado de JpaRepository para 
     *           documentación específica. La implementación real es proporcionada por Spring Data JPA.
     */
    Optional<User> findById(int id);
}

