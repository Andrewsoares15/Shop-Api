package com.shop.shopapi;

import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.producer.ShopProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ShopProducerTest {

    @InjectMocks
    private ShopProducer shopProducer;

    @Mock
    private KafkaTemplate<String, ShopDTO> kafkaTemplate;

    private static final String	SHOP_TOPIC_NAME	="TOPIC_SHOP";
    @Test
    public void testSendMessage(){
        var shopDTO = new ShopDTO();
        shopDTO.setBuyerIdentifier("b-2");
        shopProducer.send(shopDTO);
        Mockito.verify(kafkaTemplate, Mockito.times(1))
                .send(SHOP_TOPIC_NAME, "b-2", shopDTO);
    }

}
