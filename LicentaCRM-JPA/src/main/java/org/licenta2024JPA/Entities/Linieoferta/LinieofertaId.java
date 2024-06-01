package org.licenta2024JPA.Entities.Linieoferta;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LinieofertaId implements java.io.Serializable {
    private static final long serialVersionUID = 587785122743790920L;
    @Column(name = "codoferta", nullable = false)
    private String codoferta;

    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinieofertaId entity = (LinieofertaId) o;
        return Objects.equals(this.codoferta, entity.codoferta) &&
                Objects.equals(this.codprodus, entity.codprodus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codoferta, codprodus);
    }

}