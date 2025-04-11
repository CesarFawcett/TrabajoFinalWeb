package edu.unimagdalena.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.entities.Flight;
import edu.unimagdalena.exceptions.FlightNotFoundException;
import edu.unimagdalena.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void delete(Integer id) {
        flightRepository.deleteById(id);
    }
    
    @Override
    public Optional<Flight> fainId(Integer id) {
        return flightRepository.findById(id);
    }

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
      
    @Override
    public Flight createFlight(Flight newFlight) {
        return flightRepository.save(newFlight);
    }

    @Override
    public List<Flight> findFlightsByDepartureDate(String departureDate) {
        //List<Flight> flights = flightRepository.findByDepartureDate(departureDate);
        return flightRepository.findByDepartureDate(departureDate);
    }

    @Override
    public List<Flight> findFlightsByDepartureDateAndDepartureAirportCode(String departureDate,
            String departureAirportCode) {
        //List<Flight> flights = flightRepository.findFlightsByDepartureDateAndDepartureAirportCode(departureDate,
          //   departureAirportCode);
             
        return flightRepository.findByDepartureDateAndDepartureAirportCode(departureDate,departureAirportCode);
    }

    @Override
    public List<Flight> findFlightsByDepartureDateAndArrivalAirPortCode(String departureDate,
            String arrivalAirportCode) {
        //List<Flight> flights = flightRepository.findFlightsByDepartureDateAndArrivalAirPortCode(departure,
          //    arrivalAirportCode);

        return flightRepository.findByDepartureDateAndArrivalAirPortCode(departureDate,arrivalAirportCode);
    }

    @Override
    public List<Flight> findFlightsByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,
            String departureAirportCode, String arrivalAirportCode) {
          return flightRepository.findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(departureDate, departureAirportCode, arrivalAirportCode);
        }

    @Override
    public List<Flight> findFlightsByArrivalAirportCodeAndDepartureAirporCode(String arrivalAirportCode,
            String departureAirportCode) {
           return flightRepository.findByArrivalAirportCodeAndDepartureAirportCode(arrivalAirportCode, departureAirportCode);
        }

    @Override
    public Flight getFlightById(int flightId) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isPresent()) {
            return optionalFlight.get();
        } else {
            throw new FlightNotFoundException();
        }
    }

    @Override
    public List<Flight> findByDepartureAirportCodeAndDepartureDate(String departureAirportCode, String departureDate) {
        return flightRepository.findFlightByDepartureDateAndDepartureAirportCode(departureDate, departureAirportCode);
    }
   
    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
 }
