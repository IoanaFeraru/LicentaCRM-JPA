package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Linieoferta.Linieoferta;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "oferta")
public class Oferta extends AbstractEntity {
    @Id
    @Column(name = "codoferta", nullable = false)
    private String codoferta;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tipreducere", nullable = false)
    private String tipreducere;

    @Column(name = "valoarereducere")
    private Double valoarereducere;

    @Column(name = "costpuncte")
    private Integer costpuncte;

    @OneToMany(mappedBy = "codoferta")
    private Set<Achizitie> achizitii = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codoferta")
    private Set<Campanie> campanies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codoferta")
    private Set<Linieoferta> liniioferta = new LinkedHashSet<>();

    @Override
    public Object getId() {
        return codoferta;
    }
}
