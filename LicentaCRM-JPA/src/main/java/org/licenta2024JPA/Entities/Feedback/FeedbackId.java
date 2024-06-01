package org.licenta2024JPA.Entities.Feedback;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FeedbackId implements java.io.Serializable {
    private static final long serialVersionUID = -8551137641858453085L;
    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FeedbackId entity = (FeedbackId) o;
        return Objects.equals(this.codclient, entity.codclient) &&
                Objects.equals(this.codprodus, entity.codprodus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codclient, codprodus);
    }

}