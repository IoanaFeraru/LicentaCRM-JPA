package org.licenta2024JPA.Entities.Adresa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Metamodels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "adresa")
public class Adresa extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresa_id_gen")
    @SequenceGenerator(name = "adresa_id_gen", sequenceName = "adresa_codadresa_seq", allocationSize = 1)
    @Column(name = "codadresa", nullable = false)
    private Integer codadresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codclient", nullable = false)
    private Client codclient;

    @Enumerated(EnumType.STRING)
    @Column(name = "judet", nullable = false)
    private Judet judet;

    @Column(name = "codpostal", nullable = false)
    private Integer codpostal;

    @Column(name = "strada", nullable = false)
    private String strada;

    @Column(name = "bloc")
    private String bloc;

    @Override
    public Object getId() {
        return codadresa;
    }
}
