package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AlbanjarProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AlbanjarProduct and its DTO AlbanjarProductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AlbanjarProductMapper extends EntityMapper<AlbanjarProductDTO, AlbanjarProduct> {

    

    @Mapping(target = "deliveries", ignore = true)
    AlbanjarProduct toEntity(AlbanjarProductDTO albanjarProductDTO);

    default AlbanjarProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        AlbanjarProduct albanjarProduct = new AlbanjarProduct();
        albanjarProduct.setId(id);
        return albanjarProduct;
    }
}
