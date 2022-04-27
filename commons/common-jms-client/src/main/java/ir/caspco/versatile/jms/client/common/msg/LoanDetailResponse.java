package ir.caspco.versatile.jms.client.common.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.CustomerInfoVO;
import ir.caspco.versatile.jms.client.common.vo.LoanRowBeanVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
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
public class LoanDetailResponse {

    private BigDecimal amount;
    private String automaticPaymentAccountNumber;
    private String cbLoanNumber;
    private Integer countOfMaturedUnpaid;
    private Integer countOfPaid;
    private Integer countOfUnpaid;
    private BigDecimal discount;
    private List<LoanRowBeanVO> loanRows;
    private BigDecimal penalty;
    private BigDecimal totalMaturedUnpaidAmount;
    private BigDecimal totalPaidAmount;
    private Integer totalRecord;
    private BigDecimal totalUnpaidAmount;

    @JsonProperty("customerInfosDto")
    private List<CustomerInfoVO> customerInfos;
    private String type;
}