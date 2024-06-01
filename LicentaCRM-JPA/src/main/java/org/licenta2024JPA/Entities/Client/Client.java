package org.licenta2024JPA.Entities.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Adresa.Adresa;
import org.licenta2024JPA.Entities.Comunicare;
import org.licenta2024JPA.Entities.Feedback.Feedback;
import org.licenta2024JPA.Entities.Istoricpuncte.Istoricpuncte;
import org.licenta2024JPA.Entities.Linietagclienti.Linietagclienti;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Entities.Wishlist.Wishlist;
import org.licenta2024JPA.Metamodels.AbstractEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "client_id_gen", sequenceName = "campanie_codcampanie_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_gen")
    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "datanastere", nullable = false)
    private LocalDate datanastere;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numartelefon", nullable = false)
    private String numartelefon;

    @Column(name = "puncteloialitate", nullable = false)
    private Integer puncteloialitate=0;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NONE'")
    @Column(name = "statusmembru", nullable = false)
    private StatusMembru statusmembru = StatusMembru.NONE;

    @Column(name = "lastactive", nullable = false)
    private Instant lastactive;

    @OneToMany(mappedBy = "codclient")
    private Set<Achizitie> achizitii = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Adresa> adrese = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Comunicare> comnunicari = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Feedback> feedbacks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Istoricpuncte> istoricuripuncte = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Linietagclienti> linietagclienti = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codclient")
    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    @Override
    public Object getId() {
        return codclient;
    }
}