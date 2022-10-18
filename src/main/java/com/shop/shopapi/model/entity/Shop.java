package com.shop.shopapi.model.entity;

import com.shop.shopapi.model.DTO.ShopDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "identifier", nullable = false)
    private String identifier;

    @Column(name = "buyer_identifier", nullable = false)
    private String buyerIdentifier;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "date_shop", nullable = false)
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "shop",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL) // relembrar cascade
    private List<ShopItem> items;

    public static Shop toShop(ShopDTO shopDTO){
        var shop = new Shop();
        shop.setIdentifier(shopDTO.getIdentifier());
        shop.setStatus(shopDTO.getStatus());
        shop.setDateCreation(LocalDate.now());
        shop.setBuyerIdentifier(shopDTO.getBuyerIdentifier());
        shop.setItems(
                shopDTO.getItems()
                        .stream()
                        .map(e -> ShopItem.toShopItem(e))
                        .collect(Collectors.toList()));
        return shop;
    }
}
