package com.shop.shopapi.model.DTO;

import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.model.entity.ShopItem;
import com.shop.shopapi.model.entity.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopDTO {
    private String identifier;

    private Status status;

    private LocalDate dateCreation;

    private List<ShopItemDTO> items;

    public static ShopDTO toShopDTO(Shop shop){
        var shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setDateCreation(shop.getDateCreation());
        shopDTO.setItems(
                shop.getItems()
                        .stream()
                        .map(e -> ShopItemDTO.toShopItemDTO(e))
                        .collect(Collectors.toList()));
        return shopDTO;
    }
}
