package br.com.shop.validator.consumer;

import br.com.shop.validator.repository.ProductRepository;
import br.com.shop.validator.service.ValidateShop;
import br.com.shop.validator.model.DTO.ShopDTO;
import br.com.shop.validator.model.DTO.ShopItemDTO;
import br.com.shop.validator.producer.SendTopicValidateShopError;
import br.com.shop.validator.producer.SendTopicValidateShopSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private final static String ShopDTO_TOPIC_NAME = "TOPIC_ShopDTO";

    private ProductRepository productRepository;

    private ValidateShop validateShop;

    private SendTopicValidateShopError sendTopicValidateShopDTOError;

    private SendTopicValidateShopSuccess sendTopicValidateShopDTOSucess;

    @KafkaListener(topics = ShopDTO_TOPIC_NAME, groupId = "consumerValidateShopDTO", containerFactory = "kakfaConsumer")
    public void listenShopDTOTopic(ShopDTO ShopDTO) {
        log.info("message received {} ", ShopDTO.getIdentifier());
        try{
            boolean sucess = true;
            for(ShopItemDTO ShopDTOItemDTO : ShopDTO.getItems()){
                var product = productRepository.findByIdentifier(ShopDTOItemDTO.getProductIdentifier());
                boolean validate = validateShop.isValidateShopDTO(product, ShopDTOItemDTO);
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
