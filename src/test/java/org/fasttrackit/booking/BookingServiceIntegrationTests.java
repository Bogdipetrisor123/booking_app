package org.fasttrackit.booking;

import org.fasttrackit.booking.domain.Booking;
import org.fasttrackit.booking.exception.ResourceNotFoundException;
import org.fasttrackit.booking.service.BookingService;
import org.fasttrackit.booking.transfer.SaveBookingRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceIntegrationTests {
	@Autowired
	private BookingService bookingService;


	@Test
	public void testCreateBooking_whenRequestIsValid_thenBookingIsSaved() {
		createBooking();

	}


	@Test(expected = TransactionSystemException.class)
	public void testCreateBooking_whenInvalidRequest_thenThrowExceptions(){

		SaveBookingRequest request = new SaveBookingRequest();
		bookingService.createBooking(request);

	}

	@Test
	public void testGetBooking_whenExistingBooking(){
		Booking createdBooking = createBooking();
		Booking retrievingBooking = bookingService.getBooking(createdBooking.getId());
		assertThat(retrievingBooking, notNullValue());
		assertThat(createdBooking.getId(), is(retrievingBooking.getId()));
		assertThat(createdBooking.getPrice(),is(retrievingBooking.getPrice()));
		assertThat(createdBooking.getNumberOfPersons(),is(retrievingBooking.getNumberOfPersons()));
		assertThat(createdBooking.getDestination(),is(retrievingBooking.getDestination()));
		assertThat(createdBooking.getCheckIn(),is(retrievingBooking.getCheckIn()));
		assertThat(createdBooking.getCheckOut(),is(retrievingBooking.getCheckOut()));
		assertThat(createdBooking.getImageUrl(),is(retrievingBooking.getImageUrl()));


	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetBooking_whenBookingNotExisting_thenThrowExceptions(){
		bookingService.getBooking(4000);
	}

	@Test
	public void testUpdateBooking__whenExistingBooking_thenBookingIsDeleted(){
		Booking createdBooking = createBooking();
		SaveBookingRequest request = new SaveBookingRequest();

		request.setDescription(createdBooking.getDescription() + "updated");
		request.setPrice(createdBooking.getPrice() + 300);
		request.setNumberOfPersons(createdBooking.getNumberOfPersons() + 3);
		request.setDestination(createdBooking.getDestination() + "updated");
		request.setPrice(createdBooking.getPrice() + 300);
		request.setCheckIn(createdBooking.getCheckIn().minusMonths(2));
		request.setCheckOut(createdBooking.getCheckOut().minusMonths(2).minusDays(3));
		Booking updatingBooking = bookingService.updateBooking(createdBooking.getId(), request);

	}










	private Booking createBooking() {
		SaveBookingRequest request = new SaveBookingRequest();
		request.setCheckIn(LocalDate.of(2019,8,10));
		request.setCheckOut(LocalDate.of(2019,8,17));
		request.setDestination("Turkey");
		request.setNumberOfPersons(2);
		request.setPrice(1000.0);
		request.setDescription("5*Hotel");
		Booking booking = bookingService.createBooking(request);

		assertThat(booking, notNullValue());
		assertThat(booking.getId(),greaterThan(0L));
		assertThat(booking.getCheckIn(), is(request.getCheckIn()));
		assertThat(booking.getCheckOut(), is(request.getCheckOut()));
		assertThat(booking.getDestination(), is(request.getDestination()));
		assertThat(booking.getNumberOfPersons(), is(request.getNumberOfPersons()));
		assertThat(booking.getPrice(),is(request.getPrice()));
		return booking;
	}






}
