package edu.unimagdalena.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unimagdalena.entities.Flight;
import edu.unimagdalena.exceptions.FlightNotFoundException;
import edu.unimagdalena.repositories.FlightRepository;

/**
 * Implementación concreta de {@link FlightService} que gestiona operaciones de vuelos.
 * Proporciona lógica de negocio para gestionar vuelos y delega la persistencia a {@link FlightRepository}.
 * 
 * <p>Esta implementación incluye validaciones básicas y manejo de excepciones.</p>
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * {@inheritDoc}
     * @implNote Delega la operación a Spring Data JPA. No lanza excepción si el ID no existe.
     */
    @Override
    public void delete(Integer id) {
        flightRepository.deleteById(id);
    }
    
    /**
     * {@inheritDoc}
     * @deprecated Este método será eliminado en versiones futuras. Usar {@link #getFlightById(int)} en su lugar.
     */
    @Override
    public Optional<Flight> fainId(Integer id) {
        return flightRepository.findById(id);
    }

     /**
     * {@inheritDoc}
     * @implNote Realiza una actualización parcial - solo campos no nulos en flightToUpdate.
     * @throws IllegalArgumentException si flightToUpdate es nulo.
     */
    @Override
    public Optional<Flight> updateFlight(Integer id, Flight flightToUpdate) {
        return flightRepository.findById(id).map(existingFlight -> {
            existingFlight.setDepartureDate(flightToUpdate.getDepartureDate());
            existingFlight.setDepartureAirportCode(flightToUpdate.getDepartureAirportCode());
            existingFlight.setDepartureCity(flightToUpdate.getDepartureCity());
            existingFlight.setArrivalDate(flightToUpdate.getArrivalDate());
            existingFlight.setArrivalAirportCode(flightToUpdate.getArrivalAirportCode());
            existingFlight.setArrivalCity(flightToUpdate.getArrivalCity());
            existingFlight.setTicketPrice(flightToUpdate.getTicketPrice());
            existingFlight.setTicketCurrency(flightToUpdate.getTicketCurrency());
            existingFlight.setFlightNumber(flightToUpdate.getFlightNumber());
            existingFlight.setSeatCapacity(flightToUpdate.getSeatCapacity());   
            return flightRepository.save(existingFlight);
        });
    } 
    
    /**
     * {@inheritDoc}
     * @implNote Asigna automáticamente un ID al nuevo vuelo.
     * @throws IllegalArgumentException si newFlight es nulo.
     */
    @Override
    public Flight createFlight(Flight newFlight) {
        return flightRepository.save(newFlight);
    }

     /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si departureDate es nulo o vacío.
     */
    @Override
    public List<Flight> findFlightsByDepartureDate(String departureDate) {
        //List<Flight> flights = flightRepository.findByDepartureDate(departureDate);
        return flightRepository.findByDepartureDate(departureDate);
    }

     /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    @Override
    public List<Flight> findFlightsByDepartureDateAndDepartureAirportCode(String departureDate,
            String departureAirportCode) {
        //List<Flight> flights = flightRepository.findFlightsByDepartureDateAndDepartureAirportCode(departureDate,
          //   departureAirportCode);
             
        return flightRepository.findByDepartureDateAndDepartureAirportCode(departureDate,departureAirportCode);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    @Override
    public List<Flight> findFlightsByDepartureDateAndArrivalAirPortCode(String departureDate,
            String arrivalAirportCode) {
        //List<Flight> flights = flightRepository.findFlightsByDepartureDateAndArrivalAirPortCode(departure,
          //    arrivalAirportCode);

        return flightRepository.findByDepartureDateAndArrivalAirPortCode(departureDate,arrivalAirportCode);
    }

     /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    @Override
    public List<Flight> findFlightsByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,
            String departureAirportCode, String arrivalAirportCode) {
          return flightRepository.findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(departureDate, departureAirportCode, arrivalAirportCode);
        }

        /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    @Override
    public List<Flight> findFlightsByArrivalAirportCodeAndDepartureAirporCode(String arrivalAirportCode,
            String departureAirportCode) {
           return flightRepository.findByArrivalAirportCodeAndDepartureAirportCode(arrivalAirportCode, departureAirportCode);
        }

        /**
     * {@inheritDoc}
     * @throws FlightNotFoundException si no se encuentra el vuelo.
     * @throws IllegalArgumentException si flightId ≤ 0.
     */
    @Override
    public Flight getFlightById(int flightId) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isPresent()) {
            return optionalFlight.get();
        } else {
            throw new FlightNotFoundException();
        }
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    @Override
    public List<Flight> findByDepartureAirportCodeAndDepartureDate(String departureAirportCode, String departureDate) {
        return flightRepository.findFlightByDepartureDateAndDepartureAirportCode(departureDate, departureAirportCode);
    }
   
    /**
     * {@inheritDoc}
     * @implNote Para grandes volúmenes de datos, considerar implementar paginación.
     */
    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
 }
