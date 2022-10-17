package br.com.shop.validator.producer;

import br.com.shop.validator.model.DTO.ShopDTO;
import br.com.shop.validator.model.DTO.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendTopicValidateShopError {

    @Autowired
    private KafkaTemplate<String, ShopDTO> kafkaTemplate;

    private final static String ShopDTO_TOPIC_VALIDATE_NAME = "TOPIC_SHOP_VALIDATE";

    public void send(ShopDTO ShopDTO) {
        log.info("Shop validate with error, status {}", ShopDTO);
        ShopDTO.setStatus(Status.ERROR);
        kafkaTemplate.send(ShopDTO_TOPIC_VALIDATE_NAME, ShopDTO);
    }
}
