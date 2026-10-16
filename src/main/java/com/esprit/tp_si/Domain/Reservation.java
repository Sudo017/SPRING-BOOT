package com.esprit.tp_si.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne(
            mappedBy = "reservation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private Contrat contrat;
}