package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.InternalAccountTransferRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.InternalAccountTransferResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.InternalAccountTransferEntranceVO;
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
        InternalAccountTransferClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class InternalAccountTransferClientTest {

    @Autowired
    InternalAccountTransferClient internalAccountTransferClient;

    @Test
    void transfer() {

        InternalAccountTransferRequest internalAccountTransferRequest = InternalAccountTransferRequest.builder()
                .internalAccountTransferEntrance(
                        InternalAccountTransferEntranceVO.builder()
                                .sourceCardNumber("6221061210992386")
                                .sourceSegmentCode("01")
                                .amount(BigDecimal.valueOf(1L))
                                .destinationCardNumber("6221061210992394")
                                .destinationSegmentCode("01")
                                .checkUniqueTrackingCode(false)
                                .clientTrackingCode("444254")
                                .build()
                )
                .build();

        InternalAccountTransferResponse internalAccountTransferResponse = internalAccountTransferClient.send(internalAccountTransferRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(internalAccountTransferResponse);
    }
}