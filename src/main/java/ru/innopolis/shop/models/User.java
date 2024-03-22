package ru.innopolis.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="user_test")
public class User {
    public enum State {
        NOT_CONFIRMED, CONFIRMED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private Integer age;

    private String email;
    private String password;

    @Column(name="confirm_code")
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
