package org.fasttrackit.booking.service;

import org.fasttrackit.booking.domain.Booking;
import org.fasttrackit.booking.exception.ResourceNotFoundException;
import org.fasttrackit.booking.persistence.BookingRepository;
import org.fasttrackit.booking.transfer.GetBookingsRequest;
import org.fasttrackit.booking.transfer.SaveBookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);


    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(SaveBookingRequest request) {

        LOGGER.info("Creating Booking {}", request);
        Booking booking = new Booking();
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setDestination(request.getDestination());
        booking.setNumberOfPersons(request.getNumberOfPersons());
        booking.setPrice(request.getPrice());
        booking.setDescription(request.getDescription());


        return bookingRepository.save(booking);

    }

    public Booking getBooking(long id) {
        LOGGER.info("Getting booking {}", id);

        return bookingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booking " + id + " doesn't exist."));


    }

    public Page<Booking> getBookings(GetBookingsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving bookings: {}", request);
        if (request != null && request.getPartialDestination() != null && request.getCheckIn() != null && request.getCheckOut() != null) {
            return bookingRepository.findByDestinationAndCheckInExistsAndCheckOutExists(request.getPartialDestination(), request.getCheckIn(), request.getCheckOut(), pageable);
        } if (request != null && request.getPartialDestination() != null) {
            return bookingRepository.findByDestinationContaining(request.getPartialDestination(), pageable);
        } else return bookingRepository.findAll(pageable);
    }

    public Booking updateBooking(long id, SaveBookingRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Booking booking = getBooking(id);
        BeanUtils.copyProperties(request, booking);
        return bookingRepository.save(booking);

    }

    public void deleteBooking(long id) {
        LOGGER.info("Deleting booking : {}", id);
        bookingRepository.deleteById(id);
        LOGGER.info("Deleted succesfully");
    }


}
