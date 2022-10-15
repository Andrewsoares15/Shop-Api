package br.com.shop.validator.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_identifier",  nullable = false)
    private String identifier;

    @Column(nullable = false)
    private Double price;
}
