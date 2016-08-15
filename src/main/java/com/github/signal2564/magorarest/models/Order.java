package com.github.signal2564.magorarest.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "client_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
