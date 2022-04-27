package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.CustomerExistenceInquiryRequest;
import ir.caspco.versatile.jms.client.common.msg.CustomerExistenceInquiryResponse;
import ir.caspco.versatile.jms.client.common.vo.UserRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
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
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        CustomerClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
//TODO from davood akbari: Complete the test.
class GetCustomerInfoTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void getFullCustomerInfo() {

        CustomerExistenceInquiryRequest customerExistenceInquiryRequest = CustomerExistenceInquiryRequest.builder()
                .requestDto(
                        UserRequestVO.builder()
                                .customerNo("42003582")
                                .build()
                )
                .build();


        CustomerExistenceInquiryResponse customerExistenceInquiryResponse
                = customerClient.send(customerExistenceInquiryRequest)
                .retrieve()
                .result()
                .orElse(null);

    }

}