package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
