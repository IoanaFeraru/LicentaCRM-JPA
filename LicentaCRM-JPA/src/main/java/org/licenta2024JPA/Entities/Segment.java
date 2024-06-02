package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Entities.Linietagclienti.Linietagclienti;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "segment")
public class Segment extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "segment_id_gen")
    @SequenceGenerator(name = "segment_id_gen", sequenceName = "segment_codsegment_seq", allocationSize = 1)
    @Column(name = "codsegment", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "datacreare", nullable = false)
    private String datacreare;

    @Column(name = "criterii")
    private String criterii;

    @OneToMany(mappedBy = "codsegment")
    private Set<Comunicare> comunicares = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codsegment")
    private Set<Linietagclienti> linietagclienti = new LinkedHashSet<>();
}
