package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.InternetPackageOperator;
import ir.caspco.versatile.jms.client.common.msg.SearchInternetPackagesRequest;
import ir.caspco.versatile.jms.client.common.msg.SearchInternetPackagesResponse;
import ir.caspco.versatile.jms.client.common.vo.InternetPackageSearchVO;
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
        SearchInternetPackagesClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class SearchInternetPackagesClientTest {

    @Autowired
    private SearchInternetPackagesClient searchInternetPackagesClient;

    @Test
    void searchInternetPackages() {
        SearchInternetPackagesRequest searchInternetPackagesRequest = SearchInternetPackagesRequest.builder()
                .internetPackageSearch(
                        InternetPackageSearchVO.builder()
                                .operator(InternetPackageOperator.MCI)
//                                .packageType(PackageType.CREDIT_SIM)
//                                .durationCredit(PackageDurationCredit.ONE_DAY.value())
//                                .fromAmount(BigDecimal.ONE)
//                                .toAmount(new BigDecimal(12000))
                                .build()
                )

                .build();

        SearchInternetPackagesResponse searchInternetPackagesResponse = searchInternetPackagesClient.send(searchInternetPackagesRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(searchInternetPackagesResponse);
    }
}