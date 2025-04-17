package edu.unimagdalena.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.unimagdalena.entities.Flight;

/**
 * Repositorio para operaciones de base de datos relacionadas con {@link Flight}.
 * Proporciona métodos para consultar vuelos basados en criterios como fechas, aeropuertos y combinaciones.
 */

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    
    /**
     * Busca vuelos por fecha de salida exacta.
     * @param departureDate Fecha de salida en formato String (ej: "2025-04-20").
     * @return Lista de vuelos que coinciden con la fecha (vacía si no hay resultados).
     * @implNote Usa comparación directa de fechas. Asegúrese de que el formato coincida con el almacenado en BD.
     */    
    @Query("SELECT p FROM Flight p WHERE p.departureDate = :departureDate ")
    List<Flight> findByDepartureDate(String departureDate);
    
     /**
     * Busca vuelos por fecha, aeropuerto de salida y aeropuerto de llegada.
     * @param departureDate Fecha de salida.
     * @param departureAirportCode Código IATA del aeropuerto de salida (ej: "MAD").
     * @param arrivalAirportCode Código IATA del aeropuerto de llegada (ej: "JFK").
     * @return Lista de vuelos que cumplen los tres criterios.
     * @apiNote Útil para búsquedas específicas de rutas completas.
     */
    @Query("SELECT t FROM Flight t WHERE t.departureDate = :departureDate AND t.departureAirportCode = :departureAirportCode AND t.arrivalAirportCode = :arrivalAirportCode")
    List<Flight> findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,String departureAirportCode, String arrivalAirportCode);
    
    /**
     * Busca vuelos por fecha y aeropuerto de salida.
     * @param departureDate Fecha de salida.
     * @param departureAirportCode Código del aeropuerto de origen.
     * @return Lista de vuelos filtrados.
     */
    @Query("SELECT c FROM Flight c WHERE c.departureDate = :departureDate AND c.departureAirportCode = :departureAirportCode")
    List<Flight> findByDepartureDateAndDepartureAirportCode(String departureDate, String departureAirportCode);
    
    /**
     * Busca vuelos por fecha y aeropuerto de llegada.
     * @param departureDate Fecha de salida.
     * @param arrivalAirportCode Código del aeropuerto de destino.
     * @return Lista de vuelos filtrados.
     * @implNote Similar a {@link #findByDepartureDateAndDepartureAirportCode(String, String)} pero para destino.
     */
    @Query("SELECT d FROM Flight d WHERE d.departureDate = :departureDate AND d.arrivalAirportCode = :arrivalAirportCode")
    List<Flight> findByDepartureDateAndArrivalAirPortCode(String departureDate, String arrivalAirportCode);
    
    /**
     * Busca vuelos por aeropuerto de salida y fecha (alternativa al método equivalente).
     * @param departureAirportCode Código del aeropuerto de origen.
     * @param departureDate Fecha de salida.
     * @return Lista de vuelos filtrados.
     */
    @Query("SELECT y FROM Flight y WHERE y.departureAirportCode = :departureAirportCode AND y.departureDate = :departureDate")
    List<Flight> findByAirportCodeAndDepartureDate(String departureAirportCode, String departureDate);
    
    /**
     * Busca vuelos por combinación de aeropuertos (sin filtro por fecha).
     * @param arrivalAirportCode Código del aeropuerto de destino.
     * @param departureAirportCode Código del aeropuerto de origen.
     * @return Lista de vuelos que conectan ambos aeropuertos.
     * @apiNote Útil para encontrar todas las rutas disponibles entre dos aeropuertos.
     */
    @Query("SELECT o FROM Flight o WHERE o.arrivalAirportCode = :arrivalAirportCode AND o.departureAirportCode = :departureAirportCode")
    List<Flight> findByArrivalAirportCodeAndDepartureAirportCode(String arrivalAirportCode,String departureAirportCode);
    
    /**
     * Busca vuelos por combinación.
     * @param departureDate Código del aeropuerto de destino.
     * @param departureAirportCode Código del aeropuerto de origen.
     */
    @Query("SELECT u FROM Flight u WHERE u.departureAirportCode = :departureAirportCode AND u.departureDate = :departureDate") 
    List<Flight> findFlightByDepartureDateAndDepartureAirportCode(String departureDate,String departureAirportCode);

}

