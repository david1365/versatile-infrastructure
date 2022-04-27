package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaCardListRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaCardListResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.HampaCardListRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        HampaCardListClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class HampaCardListClientTest {

    @Autowired
    HampaCardListClient hampaCardListClient;

    @Test
    void hampaCardList() {

        HampaCardListRequest hampaCardListRequest = HampaCardListRequest.builder()
                .hampaCardListRequest(
                        HampaCardListRequestVO.builder()
                                .nationalCode("0744328764")
                                .build()
                )
                .build();

        HampaCardListResponse hampaCardListResponse = hampaCardListClient.send(hampaCardListRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(hampaCardListResponse);

    }

}