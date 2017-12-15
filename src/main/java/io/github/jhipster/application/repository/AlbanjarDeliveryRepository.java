package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AlbanjarDelivery;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AlbanjarDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlbanjarDeliveryRepository extends JpaRepository<AlbanjarDelivery, Long> {

}
