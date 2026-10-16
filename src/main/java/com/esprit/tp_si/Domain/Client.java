package com.esprit.tp_si.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}