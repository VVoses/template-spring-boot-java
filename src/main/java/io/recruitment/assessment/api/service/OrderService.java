package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Ordre;

import java.util.List;

public interface OrderService {

    Ordre getOrder(Long orderId);

    List<Ordre> getOrders();

    Ordre addOrder(Ordre order);

    Ordre updateOrder(Long orderId, Ordre order);

    void deleteOrder(Long orderId);
}
