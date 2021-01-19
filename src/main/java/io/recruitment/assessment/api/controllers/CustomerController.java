package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.model.Customer;
import io.recruitment.assessment.api.model.OrderProduct;
import io.recruitment.assessment.api.model.Ordre;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.CustomerService;
import io.recruitment.assessment.api.service.OrderNotFoundException;
import io.recruitment.assessment.api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
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
            total += product.getQuantity()*product.getPrice();
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

    @PostMapping("/customer{id}/checkout")
    Map<Double, List<OrderProduct>> checkout(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomer(id);
        List<OrderProduct> cart = customer.getCart();
        if(cart.isEmpty()) {
            return Map.of(0.0, cart);
        } else {

            Ordre order = new Ordre();

            order.setCustomerId(id);

            List<OrderProduct> newCart = new ArrayList<>();
            for(OrderProduct each : cart) {
                OrderProduct product = new OrderProduct();
                product.setPrice(each.getPrice());
                product.setName(each.getName());
                product.setQuantity(each.getQuantity());
                newCart.add(product);
            }

            order.setItems(newCart);

            this.orderService.addOrder(order);

            customer.setCart(new ArrayList<>());
            this.customerService.updateCustomer(customer, id);

            double total = 0.0;

            for(OrderProduct each : cart) {
                total += each.getPrice()*each.getQuantity();
            }

            return Map.of(total, cart); //TODO: hard to decode for frontend
        }
    }

    //TODO: add Update and Delete for Orders

    //TODO: add proper input validation

    //TODO: add role based access
}
