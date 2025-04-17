package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import edu.unimagdalena.entities.Flight;
import edu.unimagdalena.exceptions.FlightNotFoundException;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con {@link Flight}.
 * Proporciona métodos para crear, actualizar, eliminar y consultar vuelos con diversos filtros.
 */
public interface FlightService {
      
     /**
     * Elimina un vuelo existente por su ID.
     * @param id Identificador único del vuelo a eliminar.
     * @throws FlightNotFoundException si no existe un vuelo con el ID proporcionado.
     * @throws IllegalArgumentException si el ID es nulo.
     */
    void delete(Integer id);

    /**
     * Busca un vuelo por su ID (método con nombre corregido).
     * @param id Identificador único del vuelo.
     * @return {@link Optional} conteniendo el vuelo si existe, o vacío si no se encuentra.
     * @see Optional
     * @deprecated Usar {@link #getFlightById(int)} en su lugar. Este método será eliminado en versiones futuras.
     */
    Optional<Flight> fainId(Integer id);

    /**
     * Actualiza los datos de un vuelo existente.
     * @param id Identificador del vuelo a actualizar.
     * @param flightToUpdate Objeto Flight con los nuevos datos (no debe ser nulo).
     * @return {@link Optional} con el vuelo actualizado si existe, o vacío si no se encontró.
     * @throws IllegalArgumentException si flightToUpdate es nulo o contiene datos inválidos.
     */
    Optional<Flight> updateFlight(Integer id, Flight flightToUpdate);

    /**
     * Crea un nuevo vuelo en el sistema.
     * @param newFlight Objeto Flight con los datos del nuevo vuelo (no debe ser nulo).
     * @return El vuelo creado con ID asignado.
     * @throws IllegalArgumentException si newFlight es nulo o contiene datos inválidos.
     * @throws edu.unimagdalena.exceptions.DuplicateCodigoException si ya existe un vuelo con el mismo código único.
     */
    Flight createFlight(Flight newFlight);

    /**
     * Busca vuelos por fecha de salida exacta.
     * @param departureDate Fecha en formato String (ej: "2025-04-20").
     * @return Lista de vuelos que coinciden con la fecha (vacía si no hay resultados).
     * @implNote El formato de fecha debe coincidir con el almacenado en BD (YYYY-MM-DD).
     */
    List<Flight> findFlightsByDepartureDate(String departureDate);

    /**
     * Filtra vuelos por fecha y código de aeropuerto de salida.
     * @param departureDate Fecha de salida.
     * @param departureAirportCode Código IATA del aeropuerto (ej: "MAD").
     * @return Lista de vuelos filtrados (vacía si no hay coincidencias).
     * @apiNote Útil para consultar disponibilidad de vuelos desde un aeropuerto específico en una fecha.
     */
    List<Flight> findFlightsByDepartureDateAndDepartureAirportCode(String departureDate,String departureAirportCode);

    /**
     * Filtra vuelos por fecha y código de aeropuerto de llegada.
     * @param departureDate Fecha de salida.
     * @param arrivalAirportCode Código IATA del aeropuerto de destino (ej: "JFK").
     * @return Lista de vuelos filtrados.
     * @see #findFlightsByDepartureDateAndDepartureAirportCode(String, String)
     */
    List<Flight> findFlightsByDepartureDateAndArrivalAirPortCode(String departureDate, String arrivalAirportCode);

    /**
     * Filtra vuelos por fecha, aeropuerto de salida y llegada.
     * @param departureDate Fecha de salida.
     * @param departureAirportCode Código de aeropuerto de origen.
     * @param arrivalAirportCode Código de aeropuerto de destino.
     * @return Lista de vuelos que cumplen los tres criterios.
     * @throws IllegalArgumentException si algún código de aeropuerto es nulo o vacío.
     */
    List<Flight> findFlightsByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,String departureAirportCode, String arrivalAirportCode);

    /**
     * Filtra vuelos por combinación de aeropuertos de salida y llegada (sin filtro por fecha).
     * @param arrivalAirportCode Código de aeropuerto de destino.
     * @param departureAirportCode Código de aeropuerto de origen.
     * @return Lista de vuelos que conectan ambos aeropuertos.
     * @implNote El orden de los parámetros es inverso al nombre del método por consistencia histórica.
     */
    List<Flight> findFlightsByArrivalAirportCodeAndDepartureAirporCode(String arrivalAirportCode,String departureAirportCode);

    /**
     * Obtiene un vuelo por su ID (alternativa a findById).
     * @param flightId Identificador del vuelo.
     * @return El vuelo encontrado.
     * @throws FlightNotFoundException si no existe un vuelo con el ID proporcionado.
     */
    Flight getFlightById(int flightId);

     /**
     * Filtra vuelos por código de aeropuerto de salida y fecha (similar a findFlightsByDepartureDateAndDepartureAirportCode).
     * @param departureAirportCode Código de aeropuerto de origen.
     * @param departureDate Fecha de salida.
     * @return Lista de vuelos filtrados.
     * @see #findFlightsByDepartureDateAndDepartureAirportCode(String, String)
     */
    List<Flight> findByDepartureAirportCodeAndDepartureDate(String departureAirportCode, String departureDate);

    /**
     * Obtiene todos los vuelos existentes en el sistema.
     * @return Lista completa de vuelos (vacía si no hay registros).
     * @apiNote Para grandes volúmenes de datos, considerar implementar paginación.
     */
    List<Flight> findAll();
}

