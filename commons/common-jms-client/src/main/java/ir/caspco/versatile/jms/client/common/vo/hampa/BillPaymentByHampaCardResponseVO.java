package ir.caspco.versatile.jms.client.common.vo.hampa;

import ir.caspco.versatile.jms.client.common.vo.PayedBillInformationVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
public class BillPaymentByHampaCardResponseVO {

    private PayedBillInformationVO payedBillInformation;
}
