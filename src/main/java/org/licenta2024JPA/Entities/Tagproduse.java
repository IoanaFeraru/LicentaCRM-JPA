package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Linietagproduse.Linietagproduse;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tagproduse")
public class Tagproduse extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagproduse_id_gen")
    @SequenceGenerator(name = "tagproduse_id_gen", sequenceName = "tagproduse_codtag_seq", allocationSize = 1)
    @Column(name = "codtag", nullable = false)
    private Integer id;

    @Column(name = "element", nullable = false)
    private String element;

    @OneToMany(mappedBy = "codtag")
    private Set<Linietagproduse> liniitaguriproduse = new LinkedHashSet<>();

    @Override
    public Object getId() {
        return id;
    }
}
