package edu.unimagdalena.exceptions;

/**
 * Excepción lanzada cuando no se encuentra una reserva (Booking) en el sistema.
 * Extiende de {@link RuntimeException} para no requerir declaración en la firma del método.
 */
public class BookingNotFoundException extends RuntimeException {
    /**
     * Construye una nueva excepción con el mensaje predeterminado "Booking not Found".
     */
    public BookingNotFoundException() {
        super("Booking not Found");
    }
    
    /**
     * Construye una nueva excepción con un mensaje personalizado.
     * @param message el mensaje detallado (se guarda para luego recuperarlo con getMessage())
     */
    public BookingNotFoundException(String message) {
        super(message);
    }
}
