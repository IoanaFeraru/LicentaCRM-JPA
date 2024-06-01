package org.licenta2024JPA.Entities.Linietagclienti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "linietagclienti")
public class Linietagclienti extends AbstractEntity {
    @EmbeddedId
    private LinietagclientiId id;

    @MapsId("codsegment")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codsegment", nullable = false)
    private Segment codsegment;

    @MapsId("codclient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;
}
