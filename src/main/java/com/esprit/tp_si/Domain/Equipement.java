package com.esprit.tp_si.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;

    @ManyToMany(mappedBy = "equipements")
    @ToString.Exclude
    private Set<Vehicule> vehicules = new HashSet<>();
}