package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaChargeTheCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaChargeTheCardResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.HampaChargeTheCardSegmentRequestVO;
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
        HampaChargeTheCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class HampaChargeTheCardClientTest {

    @Autowired
    HampaChargeTheCardClient hampaChargeTheCardClient;

    @Test
    void hampaCardList() {

        HampaChargeTheCardRequest hampaChargeTheCardRequest = HampaChargeTheCardRequest.builder()
                .requestDto(
                        HampaChargeTheCardSegmentRequestVO.builder()
                                .cardNumber("6221061210990869")
                                .accountref("30100579288609")
                                .segmentCode("1")
                                .amount(new BigDecimal(100))
                                .checkUniqueTrackingCode(false)
                                .clientTrackingCode("28705202542")
                                .build()
                )
                .build();

        HampaChargeTheCardResponse hampaChargeTheCardResponse = hampaChargeTheCardClient.send(hampaChargeTheCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(hampaChargeTheCardResponse);

    }

}