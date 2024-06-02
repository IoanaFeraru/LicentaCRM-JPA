package org.licenta2024JPA.Entities.Comunicare;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Campanie;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comunicare")
public class Comunicare extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comunicare_id_gen")
    @SequenceGenerator(name = "comunicare_id_gen", sequenceName = "comunicare_codcomunicare_seq", allocationSize = 1)
    @Column(name = "codcomunicare", nullable = false)
    private Integer id;

    @Column(name = "scop", nullable = false)
    private String scop;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusComunicare status;

    @Enumerated(EnumType.STRING)
    @Column(name = "metoda", nullable = false)
    private Metoda metoda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codsegment")
    private Segment codsegment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codclient")
    private Client codclient;

    @OneToMany(mappedBy = "codcomunicare")
    private Set<Campanie> campanies = new LinkedHashSet<>();
}