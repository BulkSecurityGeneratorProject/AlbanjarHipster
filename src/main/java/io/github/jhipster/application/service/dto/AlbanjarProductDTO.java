package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AlbanjarProduct entity.
 */
public class AlbanjarProductDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double pu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlbanjarProductDTO albanjarProductDTO = (AlbanjarProductDTO) o;
        if(albanjarProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarProductDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pu=" + getPu() +
            "}";
    }
}
