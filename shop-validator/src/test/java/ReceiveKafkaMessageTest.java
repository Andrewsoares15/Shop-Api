import br.com.shop.validator.consumer.ReceiveKafkaMessage;
import br.com.shop.validator.model.DTO.ShopDTO;
import br.com.shop.validator.model.DTO.ShopItemDTO;
import br.com.shop.validator.model.DTO.Status;
import br.com.shop.validator.model.entity.Product;
import br.com.shop.validator.producer.SendTopicValidateShop;
import br.com.shop.validator.repository.ProductRepository;
import br.com.shop.validator.service.ValidateShop;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTest {

    @InjectMocks
    private ReceiveKafkaMessage receiveKafkaMessage;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ValidateShop validateShop;
    @Mock
    private SendTopicValidateShop sendTopicValidateShop;

    private ShopDTO getShopDTO() {
        ShopDTO	shopDTO	=	new	ShopDTO();
        shopDTO.setBuyerIdentifier("b-1");
        ShopItemDTO shopItemDTO	=	new	ShopItemDTO();
        shopItemDTO.setAmount(1);
        shopItemDTO.setProductIdentifier("product-1");
        shopItemDTO.setPrice(100.00);
        shopDTO.setItems(Arrays.asList(shopItemDTO));
        return	shopDTO;
    }
    private Product getProduct() {
        Product	product	=	new	Product();
        product.setAmount(1000);
        product.setId(1);
        product.setIdentifier("product-1");
        return	product;
    }
    @Test
    public void verifyConsumerWithSucess(){

        Product product = getProduct();
        ShopDTO shopDTO = getShopDTO();

        Mockito.when(productRepository.findByIdentifier(product.getIdentifier()))
                .thenReturn(Optional.of(product));
        Mockito.when(validateShop.isValidateShopDTO(Optional.of(product), shopDTO.getItems().get(0)))
                        .thenReturn(true);
        receiveKafkaMessage.listenShopDTOTopic(shopDTO);

        Mockito.verify(sendTopicValidateShop, Mockito.times(1)).send(shopDTO, Status.EXECUTED);
    }
    @Test
    public void verifyConsumerWithError(){

        Product product = getProduct();
        ShopDTO shopDTO = getShopDTO();

        Mockito.when(productRepository.findByIdentifier(product.getIdentifier()))
                .thenReturn(Optional.of(product));
        Mockito.when(validateShop.isValidateShopDTO(Optional.of(product), shopDTO.getItems().get(0)))
                .thenReturn(false);
        receiveKafkaMessage.listenShopDTOTopic(shopDTO);

        Mockito.verify(sendTopicValidateShop, Mockito.times(1)).send(shopDTO, Status.ERROR);
    }
}
