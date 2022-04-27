package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.vo.ChangeToLoanVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ChangeBillToLoanClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class ChangeBillToLoanClientTest {

    @Autowired
    private ChangeBillToLoanClient changeBillToLoanClient;

    @Test
    void changeBillToLoan() {

        boolean isChange = changeBillToLoanClient.changeBillToLoan(
                ChangeToLoanVO.builder()
                        .cardNumber("6221066000002361")
                        .segmentCode("OTR")
                        .build()
        );

        assertTrue(isChange);
    }
}