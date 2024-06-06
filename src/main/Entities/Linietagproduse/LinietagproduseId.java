package org.licenta2024JPA.Entities.Linietagproduse;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LinietagproduseId implements java.io.Serializable {
    private static final long serialVersionUID = -4338211677672031876L;
    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Column(name = "codtag", nullable = false)
    private Integer codtag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinietagproduseId entity = (LinietagproduseId) o;
        return Objects.equals(this.codtag, entity.codtag) &&
                Objects.equals(this.codprodus, entity.codprodus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codtag, codprodus);
    }

}