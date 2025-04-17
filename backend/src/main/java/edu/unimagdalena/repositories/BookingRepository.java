package edu.unimagdalena.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;

/**
 * Repositorio para operaciones de base de datos relacionadas con {@link Booking}.
 * Extiende {@link JpaRepository} para heredar operaciones CRUD básicas.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    /**
     * Busca reservas por estado.
     * @param status Estado de la reserva (ej: {@link BookingStatus#CONFIRMED}).
     * @return Lista de reservas con el estado especificado (puede estar vacía).
     * @implNote Consulta derivada automática generada por Spring Data JPA.
     */
    List<Booking> findByStatus(BookingStatus status);
    
    /**
     * Busca reservas por nombre de usuario (username) usando JPQL explícito.
     * @param username Nombre de usuario asociado a las reservas.
     * @return Lista de reservas del usuario (vacía si no existen).
     * @see edu.unimagdalena.entities.User
     */
    @Query("SELECT b FROM Booking b JOIN b.user u WHERE u.username = :username")
    List<Booking> findByUserUsername(@Param("username") String username);
    
    /**
     * Busca reservas por combinación de estado y nombre de usuario.
     * @param status Estado de la reserva.
     * @param username Nombre de usuario asociado.
     * @return Lista filtrada de reservas (vacía si no hay coincidencias).
     * @throws IllegalArgumentException Si algún parámetro es nulo.
     */
    @Query("SELECT b FROM Booking b JOIN b.user u WHERE u.username = :username AND b.status = :status")
    List<Booking> findByStatusAndUserUsername(@Param("status") BookingStatus status, @Param("username") String username);
    
    /**
     * Busca reservas por ID de vuelo de salida.
     * @param flightId ID del vuelo de salida.
     * @return Lista de reservas para el vuelo especificado.
     * @apiNote Útil para reportes de ocupación de vuelos.
     */
    @Query("SELECT b FROM Booking b WHERE b.outboundFlight.id = :flightId")
    List<Booking> findByOutboundFlightId(@Param("flightId") Integer flightId);
    
    /**
     * Alternativa a {@link #findByUserUsername(String)} usando consulta derivada.
     * @param username Nombre de usuario (sintaxis "User_Username" por convención de Spring Data).
     * @return Lista de reservas del usuario.
     */
    List<Booking> findByUser_Username(String username);

     /**
     * Alternativa a {@link #findByStatusAndUserUsername(BookingStatus, String)} con consulta derivada.
     * @param status Estado de la reserva.
     * @param username Nombre de usuario.
     * @return Lista combinada filtrada.
     */
    List<Booking> findByStatusAndUser_Username(BookingStatus status, String username);
}

