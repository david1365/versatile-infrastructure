package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class BillConfig {

    private Long id;
    private List<BillConfigCutOfVO> billConfigCutOfs;
    private String code;
    private String title;
    private String dependentCompanyCode;
    private String serviceCode;
    private byte[] smallIcon;
    private byte[] bigIcon;
    private String printDescription;
    private Boolean onlineConnection;

    private String contractNumber;
    private String accountNumber;
    private String depositProduct;
    private Long defaultCustomer;
    private String branchCode;
    private Long cutOfID;
    private Character isActive;
    private List<PayedBill> payedBills;
    private List<PayedBill> payedBillsAfterCutOf;
    private HeadOfficeContract headOfficeContract;
    private List<PayedBillReport> payedBillReports;
    private List<BillConfigAccount> billConfigAccounts;
    private Boolean hasBulkAccount;
    private Long version;

}
