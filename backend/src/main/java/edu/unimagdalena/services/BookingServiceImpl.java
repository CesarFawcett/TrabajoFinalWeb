package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;
import edu.unimagdalena.exceptions.BookingNotFoundException;
import edu.unimagdalena.repositories.BookingRepository;

/**
 * Implementación concreta de {@link BookingService} que gestiona las operaciones de negocio
 * relacionadas con reservas (Bookings). Delega la persistencia a {@link BookingRepository}.
 *
 * <p>Esta clase sigue el patrón de inyección de dependencias a través del constructor.</p>
 */
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    /**
     * Constructor para inyección de dependencias.
     * @param bookingRepository Repositorio de reservas (no debe ser nulo).
     * @throws IllegalArgumentException si bookingRepository es nulo.
     */
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * {@inheritDoc}
     * @throws BookingNotFoundException implícita al no encontrar el ID (manejada por Spring Data).
     */
    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

     /**
     * {@inheritDoc}
     * @implNote Utiliza el método save() de Spring Data JPA que realiza un INSERT o UPDATE
     *           según si el ID es nulo o no.
     */
    @Override
    public Booking createBooking(Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    /**
     * {@inheritDoc}
     * @implNote Para grandes volúmenes de datos, considerar implementar paginación
     *           usando {@link org.springframework.data.domain.Pageable}.
     */
    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @implNote El Optional permite un manejo explícito del caso "no encontrado".
     */
    @Override
    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }
    
    /**
     * {@inheritDoc}
     * @implNote Filtrado realizado directamente por Spring Data JPA mediante
     *           consulta derivada del nombre del método.
     */
    @Override
    public List<Booking> findBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si status es nulo o customerName es nulo/vacío.
     * @implNote Realiza validación de parámetros antes de delegar al repositorio.
     */
    @Override
    public List<Booking> findBookingsByStatusAndCustomerName(BookingStatus status, String customerName) {
    if (status == null) {
        throw new IllegalArgumentException("Booking status cannot be null");
    }
    if (customerName == null || customerName.trim().isEmpty()) {
        throw new IllegalArgumentException("Customer name cannot be null or empty");
    }
    return bookingRepository.findByStatusAndUser_Username(status, customerName);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si customerName es nulo/vacío.
     * @implNote La búsqueda por nombre es case-sensitive según configuración de la BD.
     */
    @Override
    public List<Booking> findBookingsByCustomerName(String customerName) {
    if (customerName == null || customerName.trim().isEmpty()) {
        throw new IllegalArgumentException("Customer name cannot be null or empty");
    }
    return bookingRepository.findByUser_Username(customerName);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si flightId es nulo o ≤ 0.
     * @apiNote Útil para generar reportes de ocupación de vuelos específicos.
     */
    @Override
    public List<Booking> findBookingByOutboundFlight(Integer flightId) {
    if (flightId == null || flightId <= 0) {
        throw new IllegalArgumentException("Flight ID must be a positive number");
    }
    return bookingRepository.findByOutboundFlightId(flightId);
    }

   
}