package com.esprit.tp_si.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;
    private String marque;
    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;


    @OneToMany(mappedBy = "vehicule")
    @ToString.Exclude
    private List<Reservation> reservations;

    @ManyToMany
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns = @JoinColumn(name = "id_vehicule"),
        inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    @ToString.Exclude
    private Set<Equipement> equipements = new HashSet<>();
    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    private List<Maintenance> maintenances = new ArrayList<>();
}