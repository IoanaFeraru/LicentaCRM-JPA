package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Istoricpuncte.Istoricpuncte;
import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "achizitie")
public class Achizitie extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achizitie_id_gen")
    @SequenceGenerator(name = "achizitie_id_gen", sequenceName = "achizitie_codachizitie_seq", allocationSize = 1)
    @Column(name = "codachizitie", nullable = false)
    private Integer id;

    @Column(name = "dataachizitie", nullable = false)
    private LocalDate dataachizitie;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codoferta", nullable = false)
    private Oferta codoferta;

    @Column(name = "platapuncte", nullable = false)
    private Integer platapuncte;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;

    @OneToMany(mappedBy = "codachizitie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Istoricpuncte> istoricpuncte = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codachizitie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Linieachizitie> linieachizitie = new LinkedHashSet<>();

    @Column(name = "totalsuma", nullable = false)
    private Double totalSuma;
}