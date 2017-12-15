package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AlbanjarCustomer;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AlbanjarCustomer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlbanjarCustomerRepository extends JpaRepository<AlbanjarCustomer, Long> {

}
