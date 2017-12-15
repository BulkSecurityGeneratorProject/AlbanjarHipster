package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AlbanjarProduct.
 */
@Entity
@Table(name = "albanjar_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlbanjarProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "pu", nullable = false)
    private Double pu;

    @OneToMany(mappedBy = "albanjarProduct")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AlbanjarDelivery> deliveries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AlbanjarProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPu() {
        return pu;
    }

    public AlbanjarProduct pu(Double pu) {
        this.pu = pu;
        return this;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Set<AlbanjarDelivery> getDeliveries() {
        return deliveries;
    }

    public AlbanjarProduct deliveries(Set<AlbanjarDelivery> albanjarDeliveries) {
        this.deliveries = albanjarDeliveries;
        return this;
    }

    public AlbanjarProduct addDeliveries(AlbanjarDelivery albanjarDelivery) {
        this.deliveries.add(albanjarDelivery);
        albanjarDelivery.setAlbanjarProduct(this);
        return this;
    }

    public AlbanjarProduct removeDeliveries(AlbanjarDelivery albanjarDelivery) {
        this.deliveries.remove(albanjarDelivery);
        albanjarDelivery.setAlbanjarProduct(null);
        return this;
    }

    public void setDeliveries(Set<AlbanjarDelivery> albanjarDeliveries) {
        this.deliveries = albanjarDeliveries;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlbanjarProduct albanjarProduct = (AlbanjarProduct) o;
        if (albanjarProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarProduct{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pu=" + getPu() +
            "}";
    }
}
