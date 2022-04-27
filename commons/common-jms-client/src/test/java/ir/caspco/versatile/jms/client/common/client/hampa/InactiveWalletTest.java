package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.InactiveWalletRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.InactiveWalletResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.InactiveWalletEntranceVO;
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
        InactiveWalletClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class InactiveWalletTest {

    @Autowired
    InactiveWalletClient inactiveWalletClient;


    @Test
    void inactiveWallet() {

        InactiveWalletRequest inactiveWalletRequest = InactiveWalletRequest.builder()
                .inactiveWalletEntranceVO(
                        InactiveWalletEntranceVO.builder()
                                .accountNumber("60300411585601")
                                .cardNumber("6221061210991800")
                                .segmentCode("01")
                                .build()
                )
                .build();

        InactiveWalletResponse inactiveWalletResponse = inactiveWalletClient.send(inactiveWalletRequest)
                .retrieve()
                .result()
                .orElse(null);
        assertNotNull(inactiveWalletResponse);
    }
}
