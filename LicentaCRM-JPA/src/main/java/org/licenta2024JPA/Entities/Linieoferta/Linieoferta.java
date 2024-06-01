package org.licenta2024JPA.Entities.Linieoferta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Oferta;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "linieoferta")
public class Linieoferta extends AbstractEntity {
    @EmbeddedId
    private LinieofertaId id;

    @MapsId("codoferta")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codoferta", nullable = false)
    private Oferta codoferta;

    @MapsId("codprodus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codprodus", nullable = false)
    private Produs codprodus;
}
