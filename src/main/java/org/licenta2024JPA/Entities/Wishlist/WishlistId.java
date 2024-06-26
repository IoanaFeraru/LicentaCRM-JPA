package org.licenta2024JPA.Entities.Wishlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class WishlistId implements java.io.Serializable {
    private static final long serialVersionUID = -2606554359274658333L;
    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WishlistId entity = (WishlistId) o;
        return Objects.equals(this.codclient, entity.codclient) &&
                Objects.equals(this.codprodus, entity.codprodus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codclient, codprodus);
    }

}