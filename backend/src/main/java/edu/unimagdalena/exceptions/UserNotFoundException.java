package edu.unimagdalena.exceptions;

/**
 * Excepción lanzada cuando un usuario no existe en el sistema.
 * Puede usarse en autenticación o búsquedas de usuarios.
 * @implNote Hereda de {@link RuntimeException} para ser unchecked.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Construye la excepción con mensaje predeterminado "User not Found".
     */
    public UserNotFoundException() {
        super("User not Found");
    }
    
    /**
     * Construye la excepción con contexto específico.
     * @param message detalle personalizado (ej: "User with email example@test.com not registered")
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}