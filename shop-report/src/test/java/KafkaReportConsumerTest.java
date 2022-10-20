import br.com.shop.report.consumer.KafkaReportConsumer;
import br.com.shop.report.model.DTO.ShopDTO;
import br.com.shop.report.model.DTO.Status;
import br.com.shop.report.repository.ShopReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
public class KafkaReportConsumerTest {

    @InjectMocks
    private KafkaReportConsumer kafkaReportConsumer;

    @Mock
    private ShopReportRepository repository;

    private ShopDTO getShopDTO() {
        ShopDTO	shopDTO	=	new	ShopDTO();
        shopDTO.setStatus(Status.EXECUTED);
        shopDTO.setIdentifier("b1-consumer");
        return	shopDTO;
    }

    @Test
    public void listenTest(){
        var shopDTO = getShopDTO();

        kafkaReportConsumer.listen(shopDTO);

        Mockito.verify(repository, Mockito.times(1)).shopReportIncrement(shopDTO.getStatus().name());

    }
}
