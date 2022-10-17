package com.shop.shopapi.consumer;

import com.shop.shopapi.exception.ShopNotFound;
import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.repository.ShopRepository;
import com.shop.shopapi.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShopValidateConsumer {

    private final static String SHOP_TOPIC_VALIDATE_NAME = "TOPIC_SHOP_VALIDATE";

    @Autowired
    private ShopRepository repository;
    @Autowired
    private ShopService shopService;

    @KafkaListener(topics = SHOP_TOPIC_VALIDATE_NAME, containerFactory = "kafkaConsumer", groupId = "ShopEventConsumer")
    public void listen(ShopDTO shopDTO){
        log.info("validating message receive {}", shopDTO.getIdentifier());
        try {
            var shop = repository.findByIdentifier(shopDTO.getIdentifier()).get();
            log.info("message validate with success {}", shopDTO.getIdentifier());
            shopService.updateStatusShop(shop, shopDTO.getStatus());
        }catch (Exception e){
            log.warn("message validate with error {}", shopDTO.getIdentifier());
        }

    }

}
