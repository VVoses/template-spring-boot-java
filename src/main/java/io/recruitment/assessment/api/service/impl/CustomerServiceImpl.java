package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.model.Customer;
import io.recruitment.assessment.api.service.CustomerNotFoundException;
import io.recruitment.assessment.api.repository.CustomerRepository;
import io.recruitment.assessment.api.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();
        this.customerRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer, Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setUsername(newCustomer.getUsername());
                    customer.setPassword((newCustomer.getPassword()));
                    customer.setCart(newCustomer.getCart());
                    customer.setId(newCustomer.getId());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    @Override
    public Customer addCustomer(Customer newCustomer) {
        return this.customerRepository.save(newCustomer);
    }
}
