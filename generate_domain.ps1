
$targetDir = "src/main/java/com/esprit/tp_si/Domain"

$utf8NoBom = New-Object System.Text.UTF8Encoding($false)

function Write-JavaFile($filename, $content) {
    $path = Join-Path $targetDir $filename

    if (!(Test-Path $path)) {
        throw "File not found: $path"
    }

    [System.IO.File]::WriteAllText($path, $content, $utf8NoBom)
}

Write-JavaFile "Contrat.java" @"
package com.esprit.tp_si.Domain;

import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private boolean valide;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false, unique = true)
    @ToString.Exclude
    private Reservation reservation;

    @OneToMany(
        mappedBy = "contrat",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @ToString.Exclude
    private List<Paiement> paiements = new ArrayList<>();
}
"@

Write-JavaFile "Paiement.java" @"
package com.esprit.tp_si.Domain;

import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "paiements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    private BigDecimal montant;
    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_contrat", nullable = false)
    @ToString.Exclude
    private Contrat contrat;
}
"@

Write-Host "Contrat.java and Paiement.java updated successfully." -ForegroundColor Green
