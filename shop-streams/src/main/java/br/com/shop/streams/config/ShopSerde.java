package br.com.shop.streams.config;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ShopSerde implements Serde {

    private ShopDeserializer shopDeserializer = new ShopDeserializer();
    private ShopSerializer shopSerializer = new ShopSerializer();

    @Override
    public void close() {
        shopDeserializer.close();
        shopSerializer.close();
    }

    @Override
    public Serializer serializer() {
        return shopSerializer;
    }

    @Override
    public Deserializer deserializer() {
        return shopDeserializer;
    }
}
