package org.fasttrackit.booking.service;

import org.fasttrackit.booking.domain.Customer;
import org.fasttrackit.booking.exception.ResourceNotFoundException;
import org.fasttrackit.booking.persistence.CustomerRepository;
import org.fasttrackit.booking.transfer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);


    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(SaveCustomerRequest request) {

        LOGGER.info("Creating Customer {}", request);
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setEmail(request.getEmail());
        customer.setLastName(request.getLastName());

        return customerRepository.save(customer);

    }

    public Customer getCustomer(long id) {
        LOGGER.info("Getting customer {}", id);

        return customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " doesn't exist."));


    }


    public Customer updateCustomer(long id, SaveCustomerRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Customer customer = getCustomer(id);
        BeanUtils.copyProperties(request, customer);
        return customerRepository.save(customer);

    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting customer : {}", id);
        customerRepository.deleteById(id);
        LOGGER.info("Deleted succesfully");
    }


}
