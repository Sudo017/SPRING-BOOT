package com.esprit.tp_si.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "agences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "agence")
    @ToString.Exclude
    private List<Employe> employes;

    @OneToMany(mappedBy = "agence")
    @ToString.Exclude
    private List<Vehicule> vehicules;
}