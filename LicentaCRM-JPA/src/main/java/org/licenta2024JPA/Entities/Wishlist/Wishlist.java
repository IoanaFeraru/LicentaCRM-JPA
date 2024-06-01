package org.licenta2024JPA.Entities.Wishlist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist extends AbstractEntity {
    @SequenceGenerator(name = "wishlist_id_gen", sequenceName = "tagproduse_codtag_seq", allocationSize = 1)
    @EmbeddedId
    private WishlistId id;

    @MapsId("codclient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;

    @MapsId("codprodus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codprodus", nullable = false)
    private Produs codprodus;

    @Column(name = "dataadaugare", nullable = false)
    private Instant dataadaugare;
}