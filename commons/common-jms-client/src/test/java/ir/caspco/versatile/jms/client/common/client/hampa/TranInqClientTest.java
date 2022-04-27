package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.TranInqRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.TranInqResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.TranInqEntranceVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        TranInqClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class TranInqClientTest {

    @Autowired
    TranInqClient tranInqClient;

    @Test
    void tranInqList() {

        TranInqRequest tranInqRequest = TranInqRequest.builder()
                .tranInqEntranceVO(
                        TranInqEntranceVO.builder()
                                .clientTrackingCode("54351778151021")
                                .build()
                )
                .build();

        TranInqResponse tranInqResponse = tranInqClient.send(tranInqRequest)
                .retrieve()
                .result()
                .orElse(null);
        assertNotNull(tranInqResponse);
    }
}
