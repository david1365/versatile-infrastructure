package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.RevertTranRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.RevertTranResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.RevertTransactionEntranceVO;
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
        RevertTranClient.class
})


@TestPropertySource("classpath:jms-client-test.properties")
public class RevertTranClientTest {

    @Autowired
    RevertTranClient revertTranClient;


    @Test
    void tranInqList() {

        RevertTranRequest revertTranRequest = RevertTranRequest.builder()
                .revertTransactionEntranceVO(
                        RevertTransactionEntranceVO.builder()
                                .clientTrackingCode("67f6e29f-965f-4bf4-bf64-5e7da82e7b27").dealReference("B0008191001586197427")
                                .build()
                )
                .build();

        RevertTranResponse revertTranResponse = revertTranClient.send(revertTranRequest)
                .retrieve()
                .result()
                .orElse(null);
        assertNotNull(revertTranResponse);
    }
}
