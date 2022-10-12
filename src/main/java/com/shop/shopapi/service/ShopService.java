package com.shop.shopapi.service;

import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    private ShopRepository repository;

    public Shop createShop(ShopDTO shopDTO){
        var shop = Shop.toShop(shopDTO);
        return repository.save(shop);
    }
}
