package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AlbanjarDelivery.
 */
@Entity
@Table(name = "albanjar_delivery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlbanjarDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "czone", nullable = false)
    private String czone;

    @NotNull
    @Column(name = "cda", nullable = false)
    private String cda;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @ManyToOne
    private AlbanjarCustomer albanjarCustomer;

    @ManyToOne
    private AlbanjarProduct albanjarProduct;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCzone() {
        return czone;
    }

    public AlbanjarDelivery czone(String czone) {
        this.czone = czone;
        return this;
    }

    public void setCzone(String czone) {
        this.czone = czone;
    }

    public String getCda() {
        return cda;
    }

    public AlbanjarDelivery cda(String cda) {
        this.cda = cda;
        return this;
    }

    public void setCda(String cda) {
        this.cda = cda;
    }

    public Double getQuantity() {
        return quantity;
    }

    public AlbanjarDelivery quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public AlbanjarCustomer getAlbanjarCustomer() {
        return albanjarCustomer;
    }

    public AlbanjarDelivery albanjarCustomer(AlbanjarCustomer albanjarCustomer) {
        this.albanjarCustomer = albanjarCustomer;
        return this;
    }

    public void setAlbanjarCustomer(AlbanjarCustomer albanjarCustomer) {
        this.albanjarCustomer = albanjarCustomer;
    }

    public AlbanjarProduct getAlbanjarProduct() {
        return albanjarProduct;
    }

    public AlbanjarDelivery albanjarProduct(AlbanjarProduct albanjarProduct) {
        this.albanjarProduct = albanjarProduct;
        return this;
    }

    public void setAlbanjarProduct(AlbanjarProduct albanjarProduct) {
        this.albanjarProduct = albanjarProduct;
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
        AlbanjarDelivery albanjarDelivery = (AlbanjarDelivery) o;
        if (albanjarDelivery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarDelivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarDelivery{" +
            "id=" + getId() +
            ", czone='" + getCzone() + "'" +
            ", cda='" + getCda() + "'" +
            ", quantity=" + getQuantity() +
            "}";
    }
}
