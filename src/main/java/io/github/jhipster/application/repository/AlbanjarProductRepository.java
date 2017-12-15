package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AlbanjarProduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AlbanjarProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlbanjarProductRepository extends JpaRepository<AlbanjarProduct, Long> {

}
