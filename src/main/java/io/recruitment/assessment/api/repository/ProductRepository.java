package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
