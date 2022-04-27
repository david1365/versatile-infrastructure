package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.vo.CompanyPlanRequestVO;
import ir.caspco.versatile.jms.client.common.vo.SegmentCompanyPlanVO;
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
        LoadCompanyPlanClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class LoadCompanyPlanClientTest {

    @Autowired
    private LoadCompanyPlanClient loadCompanyPlanClient;

    @Test
    void loadCompanyPlan() {

        List<SegmentCompanyPlanVO> segmentCompanyPlans = loadCompanyPlanClient.loadCompanyPlan(

                CompanyPlanRequestVO.builder()
                        .companyCode("C02")
                        .requesterCode("UI")
                        .build()
        );

        assertNotNull(segmentCompanyPlans);
        assertFalse(segmentCompanyPlans.isEmpty());
    }
}