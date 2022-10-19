package br.com.shop.report.config;

import br.com.shop.report.model.DTO.ShopDTO;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String boostrapServers;

    public DefaultKafkaConsumerFactory<String, ShopDTO> kafkaConsumerFactory(){
        JsonDeserializer<ShopDTO> deserializer = new JsonDeserializer<>(ShopDTO.class);
        deserializer.setUseTypeMapperForKey(true);
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShopDTO> containerFactory(){
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ShopDTO>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return factory;
    }
}
