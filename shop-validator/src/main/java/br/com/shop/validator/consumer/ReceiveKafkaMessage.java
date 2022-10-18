package br.com.shop.validator.consumer;

import br.com.shop.validator.repository.ProductRepository;
import br.com.shop.validator.service.ValidateShop;
import br.com.shop.validator.model.DTO.ShopDTO;
import br.com.shop.validator.model.DTO.ShopItemDTO;
import br.com.shop.validator.producer.SendTopicValidateShopError;
import br.com.shop.validator.producer.SendTopicValidateShopSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
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
    private SendTopicValidateShopError sendTopicValidateShopDTOError;
    @Autowired
    private SendTopicValidateShopSuccess sendTopicValidateShopDTOSucess;

    @KafkaListener(topics = ShopDTO_TOPIC_NAME, groupId = "consumerValidateShop", containerFactory = "kakfaConsumer")
    public void listenShopDTOTopic(ShopDTO ShopDTO,
                                   @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                   @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp) {
        log.info("message received key: {} partition: {} timeStamp: {}  ", key, partition, timeStamp);
        try{
            boolean sucess = true;
            for(ShopItemDTO ShopItem : ShopDTO.getItems()){
                var product = productRepository.findByIdentifier(ShopItem.getProductIdentifier());
                boolean validate = validateShop.isValidateShopDTO(product, ShopItem);
                if(!validate){
                    sendTopicValidateShopDTOError.send(ShopDTO);
                    sucess = false;
                    break;
                }
            }
            if(sucess){
                sendTopicValidateShopDTOSucess.send(ShopDTO);
            }
        }catch (Exception e){
            log.info("error processing message received {} ", ShopDTO.getIdentifier());
        }
    }
}
