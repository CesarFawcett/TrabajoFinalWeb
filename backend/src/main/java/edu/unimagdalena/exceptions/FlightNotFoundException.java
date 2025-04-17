package edu.unimagdalena.exceptions;

/**
 * Excepción para casos donde un vuelo (Flight) no existe en la base de datos.
 */
public class FlightNotFoundException extends RuntimeException {
    /**
     * Construye la excepción con mensaje predeterminado "Flight not Found".
     */
    public FlightNotFoundException() {
        super("Flight not Found");
    }
    
    /**
     * Construye la excepción con mensaje personalizado.
     * @param message detalle del error (ej: "Flight with ID 123 not found")
     */
    public FlightNotFoundException(String message) {
        super(message);
    }
}