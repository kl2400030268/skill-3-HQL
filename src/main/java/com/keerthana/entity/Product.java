package com.keerthana.entity;

import javax.persistence.*;
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer quantity;
    private String description;

    public Product() {}

    public Product(String name, Double price, Integer quantity, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public Double getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price +
                ", quantity=" + quantity + ", description=" + description + "]";
    }
}