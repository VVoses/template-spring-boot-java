package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.model.Customer;
import io.recruitment.assessment.api.model.OrderProduct;
import io.recruitment.assessment.api.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer{id}/cart")
    List<OrderProduct> viewCart(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomer(id);

        return customer.getCart();
    }

    @PostMapping("/customer{id}/cart")
    List<OrderProduct> addCart(@PathVariable Long id, @RequestBody List<OrderProduct> cart) {
        Customer customer = this.customerService.getCustomer(id);
        customer.setCart(cart);

        return this.customerService.updateCustomer(customer, customer.getId()).getCart();
    }

    @DeleteMapping("/customer{id}/carts")
    void deleteCart(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomer(id);
        customer.setCart(new ArrayList<>());
        this.customerService.updateCustomer(customer, customer.getId());
    }

    @PutMapping("/customer{id}/cart")
    List<OrderProduct> updateCart(@PathVariable Long id, @RequestBody List<OrderProduct> cart) {
        Customer customer = this.customerService.getCustomer(id);
        customer.setCart(cart);
        this.customerService.updateCustomer(customer, id);
        return cart;
    }

    @GetMapping("/customer{id}/cart/checkout")
    Map<Double, List<OrderProduct>> checkOut(@PathVariable Long id, @PathVariable Long cartId) {
        Customer customer = this.customerService.getCustomer(id);

        List<OrderProduct> cart = customer.getCart();
        double total = 0.0;
        for(OrderProduct product : cart) {
            total += product.getQuantity()* product.getPrice();
        }

        return Map.of(total, cart);
    }

    @PostMapping("/register")
    Customer addCustomer (@RequestBody Customer customer) {
        if(customer.getCart() == null) {
            customer.setCart(new ArrayList<>());
        }
        return this.customerService.addCustomer(customer);
    }

    @DeleteMapping("customer{id}/delete")
    void deleteCustomer (@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
    }

    @PutMapping("/customer{id}")
    Customer updateCustomer (@PathVariable Long id, @RequestBody Customer customer) {
        return this.customerService.updateCustomer(customer, id);
    }






}
