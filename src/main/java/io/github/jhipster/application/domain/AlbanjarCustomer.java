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

import io.github.jhipster.application.domain.enumeration.Gender;

/**
 * A AlbanjarCustomer.
 */
@Entity
@Table(name = "albanjar_customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlbanjarCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Column(name = "cin", nullable = false)
    private String cin;

    @Column(name = "adresse")
    private String adresse;

    @OneToMany(mappedBy = "albanjarCustomer")
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

    public Gender getGender() {
        return gender;
    }

    public AlbanjarCustomer gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public AlbanjarCustomer firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public AlbanjarCustomer lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCin() {
        return cin;
    }

    public AlbanjarCustomer cin(String cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public AlbanjarCustomer adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<AlbanjarDelivery> getDeliveries() {
        return deliveries;
    }

    public AlbanjarCustomer deliveries(Set<AlbanjarDelivery> albanjarDeliveries) {
        this.deliveries = albanjarDeliveries;
        return this;
    }

    public AlbanjarCustomer addDeliveries(AlbanjarDelivery albanjarDelivery) {
        this.deliveries.add(albanjarDelivery);
        albanjarDelivery.setAlbanjarCustomer(this);
        return this;
    }

    public AlbanjarCustomer removeDeliveries(AlbanjarDelivery albanjarDelivery) {
        this.deliveries.remove(albanjarDelivery);
        albanjarDelivery.setAlbanjarCustomer(null);
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
        AlbanjarCustomer albanjarCustomer = (AlbanjarCustomer) o;
        if (albanjarCustomer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarCustomer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarCustomer{" +
            "id=" + getId() +
            ", gender='" + getGender() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", cin='" + getCin() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
