package org.licenta2024JPA.Entities.Istoricpuncte;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "istoricpuncte")
public class Istoricpuncte extends AbstractEntity {
    @EmbeddedId
    private IstoricpuncteId id;

    @MapsId("codclient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;

    @MapsId("codachizitie")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codachizitie", nullable = false)
    private Achizitie codachizitie;

    @Column(name = "valoarepuncte", nullable = false)
    private Integer valoarepuncte;

    @Column(name = "dataprocesare", nullable = false)
    private LocalDate dataprocesare;
}