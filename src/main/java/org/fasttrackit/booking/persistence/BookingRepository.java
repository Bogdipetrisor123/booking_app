package org.fasttrackit.booking.persistence;

import org.apache.tomcat.jni.Local;
import org.fasttrackit.booking.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByDestinationContaining(String partialDestination, Pageable pageable);

    Page<Booking> findByDestinationAndCheckInExistsAndCheckOutExists(String partialDestination, LocalDate checkIn, LocalDate checkOut, Pageable pageable);

}
