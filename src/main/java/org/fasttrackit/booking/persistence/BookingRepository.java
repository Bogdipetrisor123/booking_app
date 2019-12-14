package org.fasttrackit.booking.persistence;

import org.fasttrackit.booking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
