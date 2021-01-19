package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Long id);

    List<Customer> getCustomers();

    void deleteCustomer(Long id);

    Customer updateCustomer(Customer newCustomer, Long id);

    Customer addCustomer(Customer newCustomer);

}
