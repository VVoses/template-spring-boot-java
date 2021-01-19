package io.recruitment.assessment.api.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Ordre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @ElementCollection
    List<OrderProduct> items;

    Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderProduct> getItems() {
        return items;
    }

    public void setItems(List<OrderProduct> items) {
        this.items = items;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Ordre(List<OrderProduct> items, Long customerId) {
        this.items = items;
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordre)) return false;
        Ordre ordre = (Ordre) o;
        return getId().equals(ordre.getId()) && getItems().equals(ordre.getItems()) && getCustomerId().equals(ordre.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItems(), getCustomerId());
    }
}
