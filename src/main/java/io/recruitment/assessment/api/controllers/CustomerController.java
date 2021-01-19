package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.model.Customer;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.CustomerService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer{id}/cart")
    List<Product> viewCart(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomer(id);

        return customer.getCart();
    }

    @PostMapping("/customer{id}/cart")
    List<Product> addCart(@PathVariable Long id, @RequestBody List<Product> cart) {
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
    List<Product> updateCart(@PathVariable Long id, @RequestBody List<Product> cart) {
        Customer customer = this.customerService.getCustomer(id);
        customer.setCart(cart);
        this.customerService.updateCustomer(customer, id);
        return cart;
    }

    @GetMapping("/customer{id}/cart/checkout")
    Map<Double, List<Product>> checkOut(@PathVariable Long id, @PathVariable Long cartId) {
        Customer customer = this.customerService.getCustomer(id);

        List<Product> cart = customer.getCart();
        double total = 0.0;
        for(Product product : cart) {
            total += product.getQuantity()* product.getPrice();
        }

        return Map.of(total, cart);
    }

    @PostMapping("/")
    Customer addCustomer (@RequestBody Customer customer) {
        if(customer.getCart() == null) {
            customer.setCart(new ArrayList<>());
        }
        return this.customerService.addCustomer(customer);
    }


}
