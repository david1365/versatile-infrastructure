package ir.caspco.versatile.common.validation.unequal;

import ir.caspco.versatile.common.validation.annotations.ThisIsUnequal;
import ir.caspco.versatile.common.validation.annotations.Unequal;
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
@Unequal
public class UnequalSampleModel {

    @ThisIsUnequal
    private Integer integer1;

    @ThisIsUnequal
    private Integer integer2;

    @ThisIsUnequal
    private Integer integer3;
}
