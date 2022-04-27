package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.ThirdPartyGroupType;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartySubGroupByThirdPartyGroupTypeRequest;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartySubGroupByThirdPartyGroupTypeResponse;
import ir.caspco.versatile.jms.client.common.vo.ThirdPartySubGroupVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        LoadThirdPartySubGroupByThirdPartyGroupTypeClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class LoadThirdPartySubGroupByThirdPartyGroupTypeClientTest {

    @Autowired
    private LoadThirdPartySubGroupByThirdPartyGroupTypeClient loadThirdPartySubGroupByThirdPartyGroupTypeClient;

    @Test
    void loadThirdPartySubGroupByThirdPartyGroupType() {

        LoadThirdPartySubGroupByThirdPartyGroupTypeRequest loadThirdPartySubGroupByThirdPartyGroupTypeRequest =
                LoadThirdPartySubGroupByThirdPartyGroupTypeRequest.builder()
                        .thirdPartyGroupType("1")
                        .build();

        LoadThirdPartySubGroupByThirdPartyGroupTypeResponse loadThirdPartySubGroupByThirdPartyGroupTypeResponse =
                loadThirdPartySubGroupByThirdPartyGroupTypeClient.send(loadThirdPartySubGroupByThirdPartyGroupTypeRequest)
                        .retrieve()
                        .result()
                        .orElse(null);

        assertNotNull(loadThirdPartySubGroupByThirdPartyGroupTypeResponse);
    }

    @Test
    void loadByThirdPartyGroupType() {

        List<ThirdPartySubGroupVO> thirdPartySubGroups =
                loadThirdPartySubGroupByThirdPartyGroupTypeClient.loadByThirdPartyGroupType(ThirdPartyGroupType.CHARITY);

        assertNotNull(thirdPartySubGroups);
    }
}