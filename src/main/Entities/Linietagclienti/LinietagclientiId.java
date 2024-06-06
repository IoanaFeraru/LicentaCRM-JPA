package org.licenta2024JPA.Entities.Linietagclienti;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LinietagclientiId implements java.io.Serializable {
    private static final long serialVersionUID = 3300019764985692416L;
    @Column(name = "codsegment", nullable = false)
    private Integer codsegment;

    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinietagclientiId entity = (LinietagclientiId) o;
        return Objects.equals(this.codsegment, entity.codsegment) &&
                Objects.equals(this.codclient, entity.codclient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codsegment, codclient);
    }

}