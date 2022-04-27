package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.IssueCardHampaRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.IssueCardHampaResponse;
import ir.caspco.versatile.jms.client.common.vo.CardIssueHampaRequestVO;
import ir.caspco.versatile.jms.client.common.vo.NoNameCardCustomerHampaVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
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
        IssueCardHampaClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class IssueCardHampaClientTest {

    @Autowired
    IssueCardHampaClient issueCardHampaClient;


    @Test
    void register() {

        List<String> segmentCodes = new ArrayList<>();
        segmentCodes.add("2");
        segmentCodes.add("3");
//        segmentCodes.add("4");


        IssueCardHampaRequest issueCardHampaRequest = IssueCardHampaRequest.builder()
                .cardIssueHampaRequest(
                        CardIssueHampaRequestVO.builder()
                                .cardProductId(643L)
                                .noNameCardCustomerHampaModel(
                                        NoNameCardCustomerHampaVO.builder()
                                                .firstName("ali")
                                                .lastName("akbari")
                                                .fullAddress("تهران")
                                                .fullName("davood")
                                                .gender('F')
                                                .nationalId("01234567")
                                                .addressType("d")
                                                .mobileTel("09125188694")
                                                .build()
                                )
                                .segmentCodes(segmentCodes)
                                .build()
                )
                .build();

        IssueCardHampaResponse issueCardHampaResponse = issueCardHampaClient.send(issueCardHampaRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(issueCardHampaResponse);

    }

    @Test
    void assign() {

        List<String> segmentCodes = new ArrayList<>();
        segmentCodes.add("2");
        segmentCodes.add("3");
//        segmentCodes.add("4");


        IssueCardHampaRequest issueCardHampaRequest = IssueCardHampaRequest.builder()
                .cardIssueHampaRequest(
                        CardIssueHampaRequestVO.builder()
                                .cardProductId(643L)
                                .noNameCardCustomerHampaModel(
                                        NoNameCardCustomerHampaVO.builder()
                                                .nationalId("01234567")
                                                .build()
                                )
                                .segmentCodes(segmentCodes)
                                .build()
                )
                .build();

        IssueCardHampaResponse issueCardHampaResponse = issueCardHampaClient.send(issueCardHampaRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(issueCardHampaResponse);

    }

}