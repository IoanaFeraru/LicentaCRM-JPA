package org.licenta2024JPA.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licenta2024JPA.Entities.Feedback.Feedback;
import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linietagproduse.Linietagproduse;
import org.licenta2024JPA.Entities.Wishlist.Wishlist;
import org.licenta2024JPA.Entities.Linieoferta.Linieoferta;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "produs")
public class Produs extends AbstractEntity {
    @Id
    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "pret", nullable = false)
    private Double pret;

    @OneToMany(mappedBy = "codprodus")
    private Set<Feedback> feedbacks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codprodus")
    private Set<Linieachizitie> liniiachizitii = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codprodus")
    private Set<Linieoferta> liniioferte = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codprodus")
    private Set<Linietagproduse> linietagproduse = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codprodus")
    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    @Override
    public Object getId() {
        return codprodus;
    }
}
