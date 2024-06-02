package org.licenta2024JPA.Entities.Istoricpuncte;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.licenta2024JPA.Entities.Client.Client;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class IstoricpuncteId implements java.io.Serializable {
    private static final long serialVersionUID = 728671387160076184L;
    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Column(name = "codachizite", nullable = false)
    private Integer codachizite;

    public IstoricpuncteId(String client, Integer codachizite) {
        this.codclient = client;
        this.codachizite = codachizite;
    }

    public IstoricpuncteId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IstoricpuncteId entity = (IstoricpuncteId) o;
        return Objects.equals(this.codachizite, entity.codachizite) &&
                Objects.equals(this.codclient, entity.codclient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codachizite, codclient);
    }

}