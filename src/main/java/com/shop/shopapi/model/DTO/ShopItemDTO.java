package com.shop.shopapi.model.DTO;

import com.shop.shopapi.model.entity.ShopItem;
import lombok.Data;

@Data
public class ShopItemDTO {

    private String productIdentifier;

    private Integer amount;

    private Double price;

    public static ShopItemDTO toShopItemDTO(ShopItem item){
        var shopItem = new ShopItemDTO();
        shopItem.setAmount(item.getAmount());
        shopItem.setPrice(item.getPrice());
        shopItem.setProductIdentifier(item.getProductIdentifier());
        return shopItem;
    }
}
