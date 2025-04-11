package edu.unimagdalena.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fullname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public User updateWith(User user){
        return new User(this.id,
                        user.fullname,
                        user.username,
                        user.password);
    }
}
