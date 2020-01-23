package org.fasttrackit.booking.web;

import org.fasttrackit.booking.domain.Booking;
import org.fasttrackit.booking.service.BookingService;
import org.fasttrackit.booking.transfer.GetBookingsRequest;
import org.fasttrackit.booking.transfer.SaveBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
public class BookingsController {

    private final BookingService bookingService;

    @Autowired
    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody @Valid SaveBookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") Long id) {
        Booking booking = bookingService.getBooking(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Booking>> getBookings(GetBookingsRequest request, Pageable pageable) {
        Page<Booking> bookings = bookingService.getBookings(request, pageable);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @RequestBody @Valid SaveBookingRequest request) {
        Booking booking = bookingService.updateBooking(id, request);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}