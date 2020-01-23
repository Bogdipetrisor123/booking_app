package org.fasttrackit.booking.persistence;

import org.fasttrackit.booking.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
