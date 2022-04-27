package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.LoanDetailResponse;
import ir.caspco.versatile.jms.client.common.vo.LoanDetailSearchVO;
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
        LoanDetailClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class LoanDetailClientTest {

    @Autowired
    private LoanDetailClient loanDetailClient;

    @Test
    void getLoanDetail() {

        LoanDetailResponse loanDetailResponse = loanDetailClient.loanDetail(
                LoanDetailSearchVO.builder()
//                        .hasDetail(true)
//                        .cbLoanNumber("")
                        .loanNumber("85569905490605")
//                        .payStatus(PayStatus.NOT_PAID_AFTER_MATURITY)
//                        .offset(0L)
//                        .length(10L)
                        .build()
        );

        assertNotNull(loanDetailResponse);
    }
}