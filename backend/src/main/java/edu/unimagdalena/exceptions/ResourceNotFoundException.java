package edu.unimagdalena.exceptions;

/**
 * Excepción genérica para recursos no encontrados (patrón reusable).
 * Recomendada para usar como padre de excepciones más específicas.
 * @see BookingNotFoundException
 * @see FlightNotFoundException
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Construye la excepción con un mensaje obligatorio.
     * @param message debe describir el recurso no encontrado (ej: "Resource 'Order' with ID 5 not found")
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}