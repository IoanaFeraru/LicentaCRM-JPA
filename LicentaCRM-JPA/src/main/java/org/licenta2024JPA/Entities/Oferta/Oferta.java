package org.licenta2024JPA.Entities.Oferta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Campanie.Campanie;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "oferta")
@NamedQueries({
        @NamedQuery(name = "Oferta.findByTipreducere", query = "SELECT o FROM Oferta o WHERE o.tipreducere = :tipreducere")
})
public class Oferta extends AbstractEntity {
    @Id
    @Column(name = "codoferta", nullable = false)
    private String codoferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipreducere", nullable = false)
    private Tipreducere tipreducere;

    @Column(name = "valoarereducere")
    private Double valoarereducere;

    @Column(name = "costpuncte")
    private Integer costpuncte=0;

    @OneToMany(mappedBy = "codoferta")
    private Set<Achizitie> achizitii = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codoferta")
    private Set<Campanie> campanies = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codprodus")
    private Produs codprodus;

    @Override
    public Object getId() {
        return codoferta;
    }
}
