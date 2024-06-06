package org.licenta2024JPA.Entities.Linieachizitie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "linieachizitie")
public class Linieachizitie extends AbstractEntity {
    @EmbeddedId
    private LinieachizitieId id;

    @MapsId("codachizitie")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codachizitie", nullable = false)
    private Achizitie codachizitie;

    @MapsId("codprodus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codprodus", nullable = false)
    private Produs codprodus;

    @Column(name = "cantitate", nullable = false)
    private Integer cantitate;
}