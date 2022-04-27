package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.enums.hampa.HampaSettlementType;
import ir.caspco.versatile.jms.client.common.msg.hampa.MerchantSettlementHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.MerchantSettlementHampaCardResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.MerchantSettlementHampaCardRequestVO;
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
        MerchantSettlementHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class MerchantSettlementHampaCardClientTest {

    @Autowired
    MerchantSettlementHampaCardClient merchantSettlementHampaCardClient;

    @Test
    void merchantSettlementBUY() {

        MerchantSettlementHampaCardRequest merchantSettlementHampaCardRequest = MerchantSettlementHampaCardRequest.builder()
                .requestDto(
                        MerchantSettlementHampaCardRequestVO.builder()
                                .amount(BigDecimal.valueOf(1))
                                .sourceAccountNumber("60400000001467")
                                .destAccountNumber("72000122166007")
                                .settlementType(HampaSettlementType.BUY)
                                .build()
                )
                .build();

        MerchantSettlementHampaCardResponse merchantSettlementHampaCardResponse = merchantSettlementHampaCardClient.send(merchantSettlementHampaCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(merchantSettlementHampaCardResponse);

    }

    @Test
    void merchantSettlementCHARGE() {

        MerchantSettlementHampaCardRequest merchantSettlementHampaCardRequest = MerchantSettlementHampaCardRequest.builder()
                .requestDto(
                        MerchantSettlementHampaCardRequestVO.builder()
                                .amount(BigDecimal.valueOf(1))
                                .sourceAccountNumber("72000122166007")
                                .destAccountNumber("60400111124607")
                                .settlementType(HampaSettlementType.CHARGE)
                                .build()
                )
                .build();

        MerchantSettlementHampaCardResponse merchantSettlementHampaCardResponse = merchantSettlementHampaCardClient.send(merchantSettlementHampaCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(merchantSettlementHampaCardResponse);

    }

}