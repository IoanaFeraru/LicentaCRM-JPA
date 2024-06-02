package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "campanie")
public class Campanie extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campanie_id_gen")
    @SequenceGenerator(name = "campanie_id_gen", sequenceName = "campanie_codcampanie_seq", allocationSize = 1)
    @Column(name = "codcampanie", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codoferta", nullable = false)
    private Oferta codoferta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codcomunicare", nullable = false)
    private Comunicare codcomunicare;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "datastart", nullable = false)
    private LocalDate datastart;

    @Column(name = "datastop")
    private LocalDate datastop;

    @Column(name = "tip")
    private String tip;
}