package br.com.shop.streams.config;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;

public class ShopSerializer implements Serializer {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    
    private static Gson gson = new Gson();

    @Override
    public byte[] serialize(String topic, Object data) {
        String lines = gson.toJson(data);
        return lines.getBytes(CHARSET);
    }
}
