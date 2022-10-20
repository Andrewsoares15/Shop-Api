package br.com.shop.streams.services;

import br.com.shop.streams.DTO.ShopDTO;
import br.com.shop.streams.config.ShopSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class PrintShops {

    private static final String SHOP_TOPIC = "TOPIC_SHOP";

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "show-shops");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, ShopSerde.class.getName());
        StreamsBuilder builder = new StreamsBuilder();

        KStream<Object, ShopDTO> stream = builder.stream(SHOP_TOPIC);

        stream.print(Printed.toSysOut());

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), props);
        kafkaStreams.start();
    }
}
