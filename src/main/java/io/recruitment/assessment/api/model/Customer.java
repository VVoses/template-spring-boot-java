package io.recruitment.assessment.api.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Customer {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @OneToMany
    private List<Product> cart;
    private String username;
    private String password;


    public Customer(List<Product> cart, String username, String password) {
        this.cart = cart;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId().equals(customer.getId()) && getCart().equals(customer.getCart()) && getUsername().equals(customer.getUsername()) && getPassword().equals(customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCart(), getUsername(), getPassword());
    }
}
