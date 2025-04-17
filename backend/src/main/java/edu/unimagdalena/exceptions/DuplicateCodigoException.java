package edu.unimagdalena.exceptions;

/**
 * Excepción lanzada cuando se detecta un código duplicado que viola restricciones de unicidad.
 */
public class DuplicateCodigoException extends RuntimeException {
    /**
     * Construye la excepción con el mensaje predeterminado "Código de la entidad duplicado".
     */
    public DuplicateCodigoException() {
        this("Codigo de la entidad duplicado");
    }
    
    /**
     * Construye la excepción con un mensaje personalizado.
     * @param message descripción específica del error
     */
    public DuplicateCodigoException(String message) {
        super(message);
    }
}