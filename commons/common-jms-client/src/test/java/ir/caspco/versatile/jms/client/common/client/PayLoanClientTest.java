package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.PayLoanRequest;
import ir.caspco.versatile.jms.client.common.msg.PayLoanResponse;
import ir.caspco.versatile.jms.client.common.vo.LoanPaymentVO;
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
        PayLoanClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class PayLoanClientTest {

    @Autowired
    private PayLoanClient payLoanClient;

    @Test
    void payLoan() {

        PayLoanRequest payLoanRequest =
                PayLoanRequest.builder()
                        .loanPayment(
                                LoanPaymentVO.builder()
                                        .cif("65838270")
                                        .loanNumber("85569905490605")
                                        .customDepositNumber("47006147111605")
                                        .amount(BigDecimal.ONE)
                                        .build()
                        )
                        .build();

        PayLoanResponse payLoanResponse = payLoanClient.send(payLoanRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(payLoanResponse);
    }
}