package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.RegisterCreditCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.RegisterCreditCardResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.RegisterCreditCardEntranceVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

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
        RegisterCreditCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class RegisterCreditCardClientTest {

    @Autowired
    RegisterCreditCardClient registerCreditCardClient;

    @Test
    void register() {

        RegisterCreditCardRequest registerCreditCardRequest = RegisterCreditCardRequest.builder()
                .registerCreditCardEntrance(
                        RegisterCreditCardEntranceVO.builder()
                                .clientTrackingCode(UUID.randomUUID().toString())
                                .nationalCode("3934670587")
                                .companyPlanID(1L)
                                .loanAmount(BigDecimal.ONE)
                                .build()
                )
                .build();

        RegisterCreditCardResponse registerCreditCardResponse = registerCreditCardClient.send(registerCreditCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(registerCreditCardResponse);
    }
}