package br.com.shop.report.consumer;

import lombok.extern.slf4j.Slf4j;
import br.com.shop.report.model.DTO.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import br.com.shop.report.repository.ShopReportRepository;

import javax.transaction.Transactional;

@Component
@Slf4j
public class KafkaReportConsumer {

    private final static String SHOP_TOPIC_VALIDATE_NAME = "TOPIC_SHOP_VALIDATE";

    @Autowired
    private ShopReportRepository repository;

    @Transactional
    @KafkaListener(topics = SHOP_TOPIC_VALIDATE_NAME, groupId = "kafkareportconsumer", containerFactory = "containerFactory")
    public void listen(ShopDTO shopDTO){
        try{
            log.info("message receive {} ", shopDTO.getIdentifier());

            repository.shopReportIncrement(shopDTO.getStatus().name());
        }catch (Exception e){
            log.warn("message processing with error {}", shopDTO.getIdentifier());
        }
    }
}
