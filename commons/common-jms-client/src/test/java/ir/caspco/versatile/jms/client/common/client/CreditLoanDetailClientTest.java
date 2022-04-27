package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.CreditLoanDetailRequest;
import ir.caspco.versatile.jms.client.common.msg.CreditLoanDetailResponse;
import ir.caspco.versatile.jms.client.common.vo.LoanInfoVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        CreditLoanDetailClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class CreditLoanDetailClientTest {

    @Autowired
    private CreditLoanDetailClient creditLoanDetailClient;

    @Test
    void getCreditLoanDetail() {

        CreditLoanDetailRequest creditLoanDetailRequest = CreditLoanDetailRequest.builder()
                .cardNumber("6221061210999654")
                .build();

        CreditLoanDetailResponse getCreditLoanDetailResponse = creditLoanDetailClient.send(creditLoanDetailRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(getCreditLoanDetailResponse);
    }

    @Test
    void testGetCreditLoanDetail() {

        List<LoanInfoVO> loanInfos = creditLoanDetailClient.creditLoanDetail("6221061210999654");

        assertNotNull(loanInfos);
        assertFalse(loanInfos.isEmpty());
    }
}