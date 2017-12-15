package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Gender;

/**
 * A DTO for the AlbanjarCustomer entity.
 */
public class AlbanjarCustomerDTO implements Serializable {

    private Long id;

    @NotNull
    private Gender gender;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String cin;

    private String adresse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlbanjarCustomerDTO albanjarCustomerDTO = (AlbanjarCustomerDTO) o;
        if(albanjarCustomerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarCustomerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarCustomerDTO{" +
            "id=" + getId() +
            ", gender='" + getGender() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", cin='" + getCin() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
