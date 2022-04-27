package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartyConfigsRequest;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartyConfigsResponse;
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
        LoadThirdPartyConfigsByParentIdClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class LoadThirdPartyConfigsClientTest {

    @Autowired
    private LoadThirdPartyConfigsByParentIdClient loadThirdPartyConfigsByParentIdClient;

    @Test
    void loadThirdPartyConfigs() {

        LoadThirdPartyConfigsRequest loadThirdPartyConfigsRequest = LoadThirdPartyConfigsRequest.builder()
                .parentId(1L)
                .build();

        LoadThirdPartyConfigsResponse loadThirdPartyConfigsResponse = loadThirdPartyConfigsByParentIdClient.send(loadThirdPartyConfigsRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(loadThirdPartyConfigsResponse);
    }
}