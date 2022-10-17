package com.shop.shopapi.service;

import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.model.entity.ShopItem;
import com.shop.shopapi.model.entity.Status;
import com.shop.shopapi.producer.ShopProducer;
import com.shop.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;
    @Autowired
    private ShopProducer shopProducer;

    public ShopDTO saveShop(ShopDTO shopDTO){
        var shop = createShop(shopDTO);
        for (ShopItem item : shop.getItems()) {
            item.setShop(shop);
        }
        Shop save = repository.save(shop);
        ShopDTO saveShopDTO = ShopDTO.toShopDTO(save);
        shopProducer.send(saveShopDTO);
        return saveShopDTO;
    }

    public List<ShopDTO> get(){
        return repository.findAll().stream()
                .map(e -> ShopDTO.toShopDTO(e))
                .collect(Collectors.toList());
    }

    private Shop createShop(ShopDTO shopDTO){
        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setStatus(Status.PENDING);
        return Shop.toShop(shopDTO);
    }

    public void updateStatusShop(Shop shop, Status status){
        shop.setStatus(status);
        repository.save(shop);
    }
}
