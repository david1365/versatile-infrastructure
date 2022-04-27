package ir.caspco.versatile.jms.client.common.vo;

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
public class BillConfigCutOf {

    private Long id;
    private BillConfig billConfig;
    private String status;
    private Date startTime;
    private Date endTime;
    private Long version;
    private List<PayedBill> payedBills;
    private String referenceBatchNumber;

}
