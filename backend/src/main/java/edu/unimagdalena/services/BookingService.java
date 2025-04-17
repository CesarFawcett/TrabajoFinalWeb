package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con {@link Booking}.
 * Contiene lógica para crear, consultar y eliminar reservas, con filtros avanzados.
 */
public interface BookingService {

    /**
     * Elimina una reserva existente por su ID.
     * @param id Identificador único de la reserva a eliminar.
     * @throws edu.unimagdalena.exceptions.BookingNotFoundException si no existe una reserva con el ID proporcionado.
     * @implNote Realiza borrado físico en base de datos. Considerar implementar borrado lógico en futuras versiones.
     */
    void delete(Integer id);

    /**
     * Crea una nueva reserva en el sistema.
     * @param newBooking Objeto Booking con los datos de la nueva reserva (no debe ser nulo).
     * @return La reserva creada con ID asignado.
     * @throws IllegalArgumentException si newBooking es nulo o contiene datos inválidos.
     * @throws edu.unimagdalena.exceptions.DuplicateCodigoException si ya existe una reserva con el mismo código único.
     */
    Booking createBooking(Booking newBooking);

    /**
     * Obtiene todas las reservas existentes en el sistema.
     * @return Lista de todas las reservas (lista vacía si no hay registros).
     * @apiNote Para sistemas con grandes volúmenes de datos, considerar implementar paginación.
     */
    List<Booking> findAll();

    /**
     * Busca una reserva por su ID único.
     * @param id Identificador de la reserva.
     * @return {@link Optional} conteniendo la reserva si existe, o vacío si no se encuentra.
     * @see Optional
     */
    Optional<Booking> findById(Integer id);

    /**
     * Filtra reservas por estado y nombre de cliente.
     * @param status Estado de reserva a filtrar (ej: {@link BookingStatus#CONFIRMED}).
     * @param customerName Nombre completo o parcial del cliente (no sensible a mayúsculas).
     * @return Lista de reservas que cumplen ambos criterios (vacía si no hay coincidencias).
     * @implNote La búsqueda por nombre usa comparación parcial (LIKE %nombre%).
     */
    List<Booking> findBookingsByStatusAndCustomerName(BookingStatus status, String customerName);

    /**
     * Filtra reservas por estado.
     * @param status Estado de reserva a filtrar.
     * @return Lista de reservas con el estado especificado.
     * @see BookingStatus
     */
    List<Booking> findBookingsByStatus(BookingStatus status);

    /**
     * Filtra reservas por nombre de cliente.
     * @param customerName Nombre completo o parcial del cliente.
     * @return Lista de reservas asociadas al cliente (vacía si no hay coincidencias).
     */
    List<Booking> findBookingsByCustomerName(String customerName);

    /**
     * Busca reservas asociadas a un vuelo de salida específico.
     * @param flight_Id ID del vuelo de salida.
     * @return Lista de reservas para el vuelo especificado.
     * @throws edu.unimagdalena.exceptions.FlightNotFoundException si el vuelo no existe.
     * @apiNote Útil para reportes de ocupación de vuelos.
     */
    List<Booking> findBookingByOutboundFlight(Integer flight_Id);
}
