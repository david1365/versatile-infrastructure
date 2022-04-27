package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.PurchaseResponse;
import ir.caspco.versatile.jms.client.common.vo.PurchaseRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        PurchaseClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class PurchaseClientTest {

    @Autowired
    PurchaseClient purchaseClient;

    @Test
    void purchase() {

        PurchaseRequestVO purchaseRequest = PurchaseRequestVO.builder()
                .build();

        PurchaseResponse purchaseResponse = purchaseClient.purchase(purchaseRequest)
                .retrieve()
                .result()
                .orElse(null);

    }
}