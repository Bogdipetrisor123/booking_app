package org.fasttrackit.booking;

import org.fasttrackit.booking.domain.Customer;
import org.fasttrackit.booking.service.CustomerService;
import org.fasttrackit.booking.transfer.SaveCustomerRequest;
import org.fasttrackit.booking.domain.Customer;
import org.fasttrackit.booking.exception.ResourceNotFoundException;
import org.fasttrackit.booking.service.CustomerService;
import org.fasttrackit.booking.transfer.SaveCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CustomerServiceIntegrationTests {
	@Autowired
	private CustomerService customerService;


	@Test
	public void testCreateCustomer_whenRequestIsValid_thenCustomerIsSaved() {
		createCustomer();

	}


	@Test(expected = TransactionSystemException.class)
	public void testCreateCustomer_whenInvalidRequest_thenThrowExceptions(){

		SaveCustomerRequest request = new SaveCustomerRequest();
		customerService.createCustomer(request);

	}

	@Test
	public void testGetCustomer_whenExistingCustomer(){
		Customer createdCustomer = createCustomer();
		Customer retrievingCustomer = customerService.getCustomer(createdCustomer.getId());
		assertThat(retrievingCustomer, notNullValue());
		assertThat(createdCustomer.getId(), is(retrievingCustomer.getId()));
		assertThat(createdCustomer.getEmail(),is(retrievingCustomer.getEmail()));
		assertThat(createdCustomer.getFirstName(),is(retrievingCustomer.getFirstName()));
		assertThat(createdCustomer.getLastName(),is(retrievingCustomer.getLastName()));


	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetCustomer_whenCustomerNotExisting_thenThrowExceptions(){
		customerService.getCustomer(4000);
	}

	@Test
	public void testUpdateCustomer__whenExistingCustomer_thenCustomerIsDeleted(){
		Customer createdCustomer = createCustomer();
		SaveCustomerRequest request = new SaveCustomerRequest();

		request.setFirstName(createdCustomer.getFirstName() + "updated");
		request.setLastName(createdCustomer.getLastName() + "updated");
		request.setEmail(createdCustomer.getEmail() + "updated");
		Customer updatingCustomer = customerService.updateCustomer(createdCustomer.getId(), request);

	}

	private Customer createCustomer() {
		SaveCustomerRequest request = new SaveCustomerRequest();
		request.setFirstName("Petrisor");
		request.setLastName("Bogdan");
		request.setEmail("bogdipetrisor28@yahoo.com");
		Customer customer = customerService.createCustomer(request);

		assertThat(customer, notNullValue());
		assertThat(customer.getId(),greaterThan(0L));
		assertThat(customer.getFirstName(), is(request.getFirstName()));
		assertThat(customer.getLastName(), is(request.getLastName()));
		assertThat(customer.getEmail(), is(request.getEmail()));
		return customer;
	}






}
