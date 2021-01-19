package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.service.OrderNotFoundException;
import io.recruitment.assessment.api.model.Ordre;
import io.recruitment.assessment.api.repository.OrderRepository;
import io.recruitment.assessment.api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Override
    public Ordre getOrder(Long orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public List<Ordre> getOrders() {
        List<Ordre> list = new ArrayList<>();
        this.orderRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Ordre addOrder(Ordre order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Ordre updateOrder(Long orderId, Ordre newOrder) {
        return this.orderRepository.findById(orderId)
                .map(ordre -> {
                    ordre.setCustomerId(newOrder.getCustomerId());
                    ordre.setItems(newOrder.getItems());
                    return orderRepository.save(ordre);
                }).orElseGet(() -> {
                    newOrder.setId(orderId);
                    return orderRepository.save(newOrder);
                });
    }

    @Override
    public void deleteOrder(Long orderId) {
        this.orderRepository.deleteById(orderId);
    }
}
