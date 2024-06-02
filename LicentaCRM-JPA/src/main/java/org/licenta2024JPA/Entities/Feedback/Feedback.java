package org.licenta2024JPA.Entities.Feedback;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback extends AbstractEntity {
    @EmbeddedId
    private FeedbackId id;

    @MapsId("codclient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;

    @MapsId("codprodus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codprodus", nullable = false)
    private Produs codprodus;

    @Column(name = "rating")
    private Double rating;

}