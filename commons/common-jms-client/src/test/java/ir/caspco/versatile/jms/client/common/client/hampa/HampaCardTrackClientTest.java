package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.vo.CardPhysicVO;
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
        HampaCardTrackClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class HampaCardTrackClientTest {

    @Autowired
    private HampaCardTrackClient hampaCardTrackClient;

    @Test
    void cardTrack() {

        CardPhysicVO cardPhysic = hampaCardTrackClient.cardTrack("6221061210989838");

        assertNotNull(cardPhysic);
    }
}