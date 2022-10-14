package com.shop.shopapi.model.entity;

import com.shop.shopapi.model.DTO.ShopItemDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "shop_item")
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_identifier", length = 100, nullable = false)
    private String productIdentifier;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public static ShopItem toShopItem(ShopItemDTO itemDTO){
        var shopItem = new ShopItem();
        shopItem.setAmount(itemDTO.getAmount());
        shopItem.setPrice(itemDTO.getPrice());
        shopItem.setProductIdentifier(itemDTO.getProductIdentifier());
        return shopItem;
    }
}
