package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.model.Ordre;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Ordre, Long> {

}
