package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.BuyProductByHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.BuyProductByHampaCardResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.BuyProductByHampaCardRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        BuyProductByHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class BuyProductByHampaCardClientTest {

    @Autowired
    BuyProductByHampaCardClient buyProductByHampaCardClient;

    @Test
    void buyProduct() {

        BuyProductByHampaCardRequest cardOtpRegisterRequest = BuyProductByHampaCardRequest.builder()
                .buyProductByHampaCardRequest(
                        BuyProductByHampaCardRequestVO.builder()
                                .cardNumber("6221061210993053")
                                .creditCardNumber("6221061107540876")
                                .segmentCode("01")
                                .accountRef("47000486659606")
                                .amount(BigDecimal.valueOf(1L))
                                .checkUniqueTrackingCode(false)
                                .clientTrackingCode("444254")
                                .build()
                )
                .build();

        BuyProductByHampaCardResponse buyProductByHampaCardResponse = buyProductByHampaCardClient.send(cardOtpRegisterRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(buyProductByHampaCardResponse);

    }

}