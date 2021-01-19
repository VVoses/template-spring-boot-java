package io.recruitment.assessment.api.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Ordre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @OneToMany
    List<Product> items;

    @OneToOne
    Customer customer;

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Ordre(List<Product> items, Customer customer) {
        this.items = items;
        this.customer = customer;
    }
}
