package io.recruitment.assessment.api.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Product {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean isNews;
    private int quantity;

    public Product(String name, String description, Double price, Boolean isNews) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isNews = isNews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getNews() {
        return isNews;
    }

    public void setNews(Boolean news) {
        isNews = news;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getQuantity() == product.getQuantity() && getId().equals(product.getId()) && getName().equals(product.getName()) && getDescription().equals(product.getDescription()) && getPrice().equals(product.getPrice()) && isNews.equals(product.isNews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), isNews, getQuantity());
    }

}
