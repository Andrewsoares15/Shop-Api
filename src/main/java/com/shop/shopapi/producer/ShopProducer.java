package com.shop.shopapi.producer;

import com.shop.shopapi.model.DTO.ShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopProducer {

    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

    private static final String SHOP_TOPIC_NAME= "TOPIC_SHOP";

    public void send(ShopDTO shopDTO){
        kafkaTemplate.send(SHOP_TOPIC_NAME, shopDTO.getBuyerIdentifier(), shopDTO);
    }
}
