package com.shop.shopapi;

import com.shop.shopapi.consumer.ShopValidateConsumer;
import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.model.DTO.ShopItemDTO;
import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.model.entity.Status;
import com.shop.shopapi.repository.ShopRepository;
import com.shop.shopapi.service.ShopService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ShopValidateConsumerTest {

    @InjectMocks
    private ShopValidateConsumer shopValidateConsumer;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private ShopService shopService;

    @Test
    public void testeConsumerMessage(){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setStatus(Status.EXECUTED);
        shopDTO.setIdentifier("123456789");

        var shopItemDTO = new ShopItemDTO();
        shopItemDTO.setAmount(5);
        shopItemDTO.setPrice(100.00);
        shopItemDTO.setProductIdentifier("product-1");

        shopDTO.setItems(Arrays.asList(shopItemDTO));

        Shop shop = Shop.toShop(shopDTO);

        Mockito.when(shopRepository.findByIdentifier(shopDTO.getIdentifier()))
                .thenReturn(Optional.of(shop));

        shopValidateConsumer.listen(shopDTO);

        Mockito.verify(shopRepository, Mockito.times(1)).findByIdentifier(shopDTO.getIdentifier());

        Mockito.verify(shopService, Mockito.times(1)).updateStatusShop(shop, shopDTO.getStatus());

    }
}
