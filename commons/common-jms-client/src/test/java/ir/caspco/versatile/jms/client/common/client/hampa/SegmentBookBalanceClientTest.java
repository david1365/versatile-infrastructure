package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaGetSegmentBookBalanceRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaGetSegmentBookBalanceResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.HampaSegmentBookBalanceRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

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
        SegmentBookBalanceClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class SegmentBookBalanceClientTest {


    @Autowired
    SegmentBookBalanceClient segmentBookBalanceClient;

    @Test
    void getSegmentBookBalance() {

        List<String> segmentCodes = new ArrayList<>();
        segmentCodes.add("1");
        segmentCodes.add("26");
        segmentCodes.add(null);

        HampaGetSegmentBookBalanceRequest hampaGetSegmentBookBalanceRequest = HampaGetSegmentBookBalanceRequest.builder()
                .requestDto(
                        HampaSegmentBookBalanceRequestVO.builder()
                                .cardNumber("6221061210990919")
                                .segmentCodeList(segmentCodes)
                                .build()
                )
                .build();

        HampaGetSegmentBookBalanceResponse hampaGetSegmentBookBalanceResponse = segmentBookBalanceClient.send(hampaGetSegmentBookBalanceRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(hampaGetSegmentBookBalanceResponse);

    }

}