package ir.caspco.versatile.jms.client.common.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class ThirdPartyRequestVO {
    private ThirdPartyConfigVO thirdPartyConfig;
    @JsonProperty("CustomerNumber")
    private Long customerNumber;
    private String cardNumber;
    private String channelType;
    private Integer trackingNumber;
    private BigDecimal paymentAmount;
    private String cellNumber;
    private String rrn;
    private Integer serviceType;
    private String chargeCardNumber;
    private BigDecimal goldUnit;
    private BigDecimal goldUnitPrice;
    private String accountNumber;
    private String etfUserId;
    private String etfNationalCode;
    private Integer operatorId;
    private Integer productId;

    public static class ThirdPartyRequestVOBuilder {
        private ThirdPartyConfigVO thirdPartyConfig;
        private Long customerNumber;
        private String cardNumber;
        private Integer serviceType;


        public ThirdPartyRequestVOBuilder paymentServiceId(Long paymentServiceId) {
            this.thirdPartyConfig = ThirdPartyConfigVO.builder().id(paymentServiceId).build();

            return this;
        }

        public ThirdPartyRequestVOBuilder walletMobileNo(Long customerNumber) {
            this.customerNumber = customerNumber;

            return this;
        }

        public ThirdPartyRequestVOBuilder walletId(String cardNumber) {
            this.cardNumber = cardNumber;

            return this;
        }

        public ThirdPartyRequestVOBuilder chTopupServiceCode(Integer serviceType) {
            this.serviceType = serviceType;

            return this;
        }

        public ThirdPartyRequestVOBuilder mobileNo(String cellNumber) {
            this.cellNumber = cellNumber;

            return this;
        }
    }
}
