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
public class GuarantorInfoVO {

    private CustomerVO customer;
    private List<LoanCollateralVO> collateralList;
}
