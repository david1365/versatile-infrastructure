package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.ChannelType;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.InternetPackageOperator;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.ServiceType;
import ir.caspco.versatile.jms.client.common.msg.hampa.PurchaseThirdPartyRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.PurchaseThirdPartyResponse;
import ir.caspco.versatile.jms.client.common.vo.ThirdPartyConfigVO;
import ir.caspco.versatile.jms.client.common.vo.ThirdPartyRequestVO;
import ir.caspco.versatile.jms.client.common.vo.hampa.PurchaseThirdPartyEntranceVO;
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
        PurchaseThirdPartyClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class PurchaseThirdPartyClientTest {

    @Autowired
    PurchaseThirdPartyClient purchaseClient;

    @Test
    void purchaseCharityByCard() {

        PurchaseThirdPartyRequest purchaseRequest = PurchaseThirdPartyRequest.builder()
                .purchaseThirdPartyEntrance(
                        PurchaseThirdPartyEntranceVO.builder()
                                .segmentCode(null)
                                .accountRef("60400000001468")
                                .thirdPartyRequest(
                                        ThirdPartyRequestVO.builder()
                                                .thirdPartyConfig(
                                                        ThirdPartyConfigVO.builder()
                                                                .id(7L)//payment service id //config
                                                                .build()
                                                )
                                                .paymentAmount(BigDecimal.valueOf(50000))
                                                .trackingNumber(142236)
                                                .customerNumber(11542826L) //if has credit card customer number // otherwise national code
                                                .cellNumber("09366336634")
//                                                .serviceType(1)
                                                .channelType(ChannelType.PERSONAL_INTERNET_BANK.value()) //const
                                                .cardNumber("6221061210991834")
                                                .build())
                                .checkUniqueTrackingCode(true)
                                .clientTrackingCode("b9db3dff65624254995a8553bb7d803f")
                                .build()
                )
                .build();

        PurchaseThirdPartyResponse purchaseResponse = purchaseClient.send(purchaseRequest)
                .retrieve()
                .result()
                .orElse(null);


        assertNotNull(purchaseResponse);
    }

    @Test
    void purchaseDirectChargeByCard() {

        PurchaseThirdPartyRequest purchaseRequest = PurchaseThirdPartyRequest.builder()
                .purchaseThirdPartyEntrance(
                        PurchaseThirdPartyEntranceVO.builder()
                                .segmentCode(null)
                                .accountRef("60400000001468") //merchant
                                .thirdPartyRequest(
                                        ThirdPartyRequestVO.builder()
                                                .thirdPartyConfig(
                                                        ThirdPartyConfigVO.builder()
                                                                .id(342L) //config
                                                                .build()
                                                )
                                                .paymentAmount(BigDecimal.valueOf(100000))
                                                .trackingNumber(142236) // clientTrackingCode
                                                .customerNumber(11542826L)//if has credit card customer number from person// otherwise national code form person
                                                .cellNumber("09366336634") //entrance
                                                .serviceType(ServiceType.IRANCELL_POSTPAID_SIMCARD.id()) // entrance
                                                .channelType(ChannelType.PERSONAL_INTERNET_BANK.value()) //const
                                                .cardNumber("6221061210991834") //entrance
                                                .build())
                                .checkUniqueTrackingCode(false) //const
                                .clientTrackingCode("b9db3dff65624265995a8553bb8d807d")
                                .build()
                )
                .build();

        PurchaseThirdPartyResponse purchaseResponse = purchaseClient.send(purchaseRequest)
                .retrieve()
                .result()
                .orElse(null);


        assertNotNull(purchaseResponse);
    }

    @Test
    void purchasePinChargeByCard() {

        PurchaseThirdPartyRequest purchaseRequest = PurchaseThirdPartyRequest.builder()
                .purchaseThirdPartyEntrance(
                        PurchaseThirdPartyEntranceVO.builder()
                                .segmentCode(null)
                                .accountRef("60400000001468")
                                .thirdPartyRequest(
                                        ThirdPartyRequestVO.builder()
                                                .thirdPartyConfig(
                                                        ThirdPartyConfigVO.builder()
                                                                .id(2L)//payment service id //config
                                                                .build()
                                                )
                                                .paymentAmount(BigDecimal.valueOf(50000))
                                                .trackingNumber(142236)
                                                .customerNumber(11542826L) //if has credit card customer number // otherwise national code
                                                .cellNumber("09366336634")
//                                                .serviceType(1)
                                                .channelType(ChannelType.PERSONAL_INTERNET_BANK.value())
                                                .cardNumber("6221061210991834")
                                                .build())
                                .checkUniqueTrackingCode(false)
                                .clientTrackingCode("b9db2dff65624254995a8553bb7d818f")
                                .build()
                )
                .build();

        PurchaseThirdPartyResponse purchaseResponse = purchaseClient.send(purchaseRequest)
                .retrieve()
                .result()
                .orElse(null);


        assertNotNull(purchaseResponse);
    }

    @Test
    void purchaseInternetPackageByCard() {

        PurchaseThirdPartyRequest purchaseRequest = PurchaseThirdPartyRequest.builder()
                .purchaseThirdPartyEntrance(
                        PurchaseThirdPartyEntranceVO.builder()
                                .segmentCode("01")
                                .accountRef("6221061210999522")
                                .thirdPartyRequest(
                                        ThirdPartyRequestVO.builder()
                                                .thirdPartyConfig(
                                                        ThirdPartyConfigVO.builder()
                                                                .id(0L)
                                                                .build()
                                                )
                                                .paymentAmount(BigDecimal.valueOf(100000))
                                                .trackingNumber(142236)
                                                .customerNumber(11542826L)//if has credit card customer number // otherwise national code
                                                .cellNumber("09366336634")
                                                .serviceType(ServiceType.INTERNET_PACKAGE.id())//entrance
                                                .channelType(ChannelType.PERSONAL_INTERNET_BANK.value())//const
                                                .productId(123)
                                                .operatorId(InternetPackageOperator.MCI.value())
                                                .cardNumber("6221061210991834")
                                                .build())
                                .checkUniqueTrackingCode(false)
                                .clientTrackingCode("b9db2dff65624254995a8553bb7d807d")
                                .build()
                )
                .build();

        PurchaseThirdPartyResponse purchaseResponse = purchaseClient.send(purchaseRequest)
                .retrieve()
                .result()
                .orElse(null);


        assertNotNull(purchaseResponse);
    }
}