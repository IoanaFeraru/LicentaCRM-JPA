package org.licenta2024JPA.Entities.Linietagproduse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "linietagproduse")
public class Linietagproduse extends AbstractEntity {
    @EmbeddedId
    private LinietagproduseId id;

    @MapsId("codprodus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codprodus", nullable = false)
    private Produs codprodus;

    @MapsId("codtag")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codtag", nullable = false)
    private Tagproduse codtag;
}
