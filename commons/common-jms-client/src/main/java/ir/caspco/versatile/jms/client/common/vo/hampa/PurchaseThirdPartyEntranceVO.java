package ir.caspco.versatile.jms.client.common.vo.hampa;

import ir.caspco.versatile.jms.client.common.vo.ThirdPartyRequestVO;
import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 * @author Mohammad Javad Zahedi - 2021
 * mjavadzahedi0@gmail.com
 * 09377732642
 */


@Data
@Builder
public class PurchaseThirdPartyEntranceVO {

    private String segmentCode;
    private String accountRef;
    private Boolean checkUniqueTrackingCode;
    private String clientTrackingCode;
    private ThirdPartyRequestVO thirdPartyRequest;
}
