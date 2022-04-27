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
public class BillConfigVO {

    private Long id;
    private List<BillConfigCutOfVO> billConfigCutOfDtos;
    private String code;
    private String title;
    private String dependentCompanyCode;
    private String serviceCode;
    private byte[] smallIcon;
    private byte[] bigIcon;
    private String contractNumber;
    private String accountNumber;
    private String depositProduct;
    private Long defaultCustomer;
    private String branchCode;
    private Long cutOfID;
    private Boolean isActive;

    private Boolean isMultipleAccounts;
    private List<PayedBillVO> payedBillsDto;
    private List<PayedBillVO> payedBillsAfterCutOfDto;
    private HeadOfficeContractVO headOfficeContractDTO;
    private List<PayedBillReportVO> payedBillReportsDto;
    private Long version;
    private String printDescription;
    private Boolean onlineConnection;

}
