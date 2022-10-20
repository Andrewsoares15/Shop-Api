package br.com.shop.streams.config;


import br.com.shop.streams.DTO.ShopDTO;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.Charset;


public class ShopDeserializer implements Deserializer {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static Gson gson = new Gson();

    @Override
    public Object deserialize(String topic, byte[] data) {
        try{
            String shop = new String(data, CHARSET);
            return gson.fromJson(shop, ShopDTO.class);
        }catch (Exception e){
            throw new IllegalArgumentException("Error reading bytes! ", e);
        }
    }
}
