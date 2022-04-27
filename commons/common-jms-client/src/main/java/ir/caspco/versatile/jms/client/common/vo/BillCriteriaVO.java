package ir.caspco.versatile.jms.client.common.vo;


import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class BillCriteriaVO {
    private String paymentNumber;
    private String billNumber;
    private Long customerNumber;
    private String cardNumber;
    private String accountNumber;
    private Integer trackingNumber;
    private PaymentInformationVO paymentInformation;
    private String rrn;
    private String channelType;


    public static class BillCriteriaVOBuilder {
        private String paymentNumber;
        private String billNumber;
        private Long customerNumber;
        private String accountNumber;


        public BillCriteriaVOBuilder payId(String paymentNumber) {
            this.paymentNumber = paymentNumber;

            return this;
        }

        public BillCriteriaVOBuilder billId(String billNumber) {
            this.billNumber = billNumber;

            return this;
        }

        public BillCriteriaVOBuilder walletMobileNo(Long customerNumber) {
            this.customerNumber = customerNumber;

            return this;
        }

        public BillCriteriaVOBuilder walletId(String accountNumber) {
            this.accountNumber = accountNumber;

            return this;
        }
    }

}
