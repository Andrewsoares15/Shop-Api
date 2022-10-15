package br.com.shop.validator.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private final static String SHOP_TOPIC_NAME = "TOPIC_SHOP";
    private final static String SHOP_TOPIC_VALIDATE_NAME = "TOPIC_SHOP_VALIDATE";
}
