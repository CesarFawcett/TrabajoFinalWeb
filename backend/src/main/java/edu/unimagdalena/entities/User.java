package edu.unimagdalena.entities;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representa un usuario del sistema.
 * <p>
 * Contiene información de autenticación y perfil del usuario.
 */

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "Entidad que representa un usuario registrado")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único del usuario", example = "1")
    private int id;

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String fullname;
    
    @Column(nullable = false)
    @Schema(description = "Nombre de usuario para login", example = "juan123")
    private String username;

    @Column(nullable = false)
    @Schema(description = "Contraseña encriptada", example = "$2a$10$...")
    private String password;
    
    /**
     * Actualiza los campos del usuario con los valores de otro objeto User.
     */
    public User updateWith(User user){
        return new User(this.id,
                        user.fullname,
                        user.username,
                        user.password);
    }
}
