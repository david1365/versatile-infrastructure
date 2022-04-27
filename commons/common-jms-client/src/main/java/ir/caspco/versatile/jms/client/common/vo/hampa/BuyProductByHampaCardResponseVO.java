package ir.caspco.versatile.jms.client.common.vo.hampa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
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
public class BuyProductByHampaCardResponseVO {

    private String depositDealReference;
    private String creditDealReference;
    private String serverTrackingCode;
    private String clientTrackingCode;
    private String serverCreditTrackingCode;
    private Date transactionDate;
    private List<SegmentBookBalanceQueryResultVO> segmentsBookBalanceList;
}
