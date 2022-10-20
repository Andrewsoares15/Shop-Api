package br.com.shop.validator.consumer;

import br.com.shop.validator.model.DTO.Status;
import br.com.shop.validator.producer.SendTopicValidateShop;
import br.com.shop.validator.repository.ProductRepository;
import br.com.shop.validator.service.ValidateShop;
import br.com.shop.validator.model.DTO.ShopDTO;
import br.com.shop.validator.model.DTO.ShopItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceiveKafkaMessage {

    private final static String ShopDTO_TOPIC_NAME = "TOPIC_SHOP";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValidateShop validateShop;
    @Autowired
    private SendTopicValidateShop sendTopicValidateShopDTO;

    @KafkaListener(topics = ShopDTO_TOPIC_NAME, groupId = "consumerValidateShop", containerFactory = "kakfaConsumer")
    public void listenShopDTOTopic(ShopDTO shopDTO) {
        log.info("message received {} ", shopDTO.getIdentifier());
        try{
            boolean sucess = true;
            for(ShopItemDTO ShopItem : shopDTO.getItems()){
                var product = productRepository.findByIdentifier(ShopItem.getProductIdentifier());
                boolean validate = validateShop.isValidateShopDTO(product, ShopItem);
                if(!validate){
                    sendTopicValidateShopDTO.send(shopDTO, Status.ERROR);
                    sucess = false;
                    break;
                }
            }
            if(sucess){
                sendTopicValidateShopDTO.send(shopDTO, Status.EXECUTED);
            }
        }catch (Exception e){
            log.info("error processing message received {} ", shopDTO.getIdentifier());
        }
    }
}
