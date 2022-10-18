package com.shop.shopapi.model.DTO;

import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.model.entity.ShopItem;
import com.shop.shopapi.model.entity.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDTO {
    private String identifier;

    private String buyerIdentifier;

    private Status status;

    private LocalDate dateCreation;

    private List<ShopItemDTO> items;

    public static ShopDTO toShopDTO(Shop shop){
        var shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setDateCreation(shop.getDateCreation());
        shopDTO.setBuyerIdentifier(shop.getBuyerIdentifier());
        shopDTO.setItems(
                shop.getItems()
                        .stream()
                        .map(e -> ShopItemDTO.toShopItemDTO(e))
                        .collect(Collectors.toList()));
        return shopDTO;
    }
}
