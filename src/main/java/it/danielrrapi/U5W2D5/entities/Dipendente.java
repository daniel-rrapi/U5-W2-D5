package it.danielrrapi.U5W2D5.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.profilePicUrl = "https://cdn-icons-png.freepik.com/512/3135/3135715.png";
    }
}
