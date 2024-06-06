package org.licenta2024JPA.Entities.Linieachizitie;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LinieachizitieId implements java.io.Serializable {
    private static final long serialVersionUID = -3947306459414348347L;
    @Column(name = "codachizitie", nullable = false)
    private Integer codachizitie;

    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    public LinieachizitieId(Integer codachizitie, String codprodus) {
        this.codachizitie = codachizitie;
        this.codprodus = codprodus;
    }

    public LinieachizitieId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinieachizitieId entity = (LinieachizitieId) o;
        return Objects.equals(this.codachizitie, entity.codachizitie) &&
                Objects.equals(this.codprodus, entity.codprodus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codachizitie, codprodus);
    }

}