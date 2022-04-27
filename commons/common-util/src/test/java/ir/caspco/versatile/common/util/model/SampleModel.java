package ir.caspco.versatile.common.util.model;

import ir.caspco.versatile.common.util.OneNotNull;
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
public class SampleModel {
    @OneNotNull
    private String string;

    @OneNotNull
    private Integer integer;

    @OneNotNull
    private Long longNumber;
}
