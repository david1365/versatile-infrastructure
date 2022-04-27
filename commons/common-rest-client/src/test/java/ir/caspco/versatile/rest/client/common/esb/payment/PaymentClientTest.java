package ir.caspco.versatile.rest.client.common.esb.payment;

import ir.caspco.versatile.rest.client.common.esb.payment.vo.PaymentEntranceVO;
import ir.caspco.versatile.rest.client.configuration.RestClientBeanConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PaymentClient.class,
        RestClientBeanConfiguration.class

})
@TestPropertySource(locations = "classpath:application.properties")
//TODO from davood akbari: Complete the test.
class PaymentClientTest {

    @Autowired
    private PaymentClient paymentClient;

    @Test
    void paymentTest() {
        paymentClient.doPaymentWithoutLogin
                .post(PaymentEntranceVO.builder().build())
                .retrieve();
    }

}