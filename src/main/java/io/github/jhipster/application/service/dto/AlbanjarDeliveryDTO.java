package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AlbanjarDelivery entity.
 */
public class AlbanjarDeliveryDTO implements Serializable {

    private Long id;

    @NotNull
    private String czone;

    @NotNull
    private String cda;

    @NotNull
    private Double quantity;

    private Long albanjarCustomerId;

    private Long albanjarProductId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCzone() {
        return czone;
    }

    public void setCzone(String czone) {
        this.czone = czone;
    }

    public String getCda() {
        return cda;
    }

    public void setCda(String cda) {
        this.cda = cda;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getAlbanjarCustomerId() {
        return albanjarCustomerId;
    }

    public void setAlbanjarCustomerId(Long albanjarCustomerId) {
        this.albanjarCustomerId = albanjarCustomerId;
    }

    public Long getAlbanjarProductId() {
        return albanjarProductId;
    }

    public void setAlbanjarProductId(Long albanjarProductId) {
        this.albanjarProductId = albanjarProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlbanjarDeliveryDTO albanjarDeliveryDTO = (AlbanjarDeliveryDTO) o;
        if(albanjarDeliveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albanjarDeliveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbanjarDeliveryDTO{" +
            "id=" + getId() +
            ", czone='" + getCzone() + "'" +
            ", cda='" + getCda() + "'" +
            ", quantity=" + getQuantity() +
            "}";
    }
}
