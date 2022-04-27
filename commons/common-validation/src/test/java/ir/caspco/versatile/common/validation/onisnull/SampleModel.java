package ir.caspco.versatile.common.validation.onisnull;

import ir.caspco.versatile.common.validation.annotations.OneIsFull;
import ir.caspco.versatile.common.validation.annotations.OneIsNotNull;
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
@OneIsNotNull
public class SampleModel {
    @OneIsFull
    private String string;

    @OneIsFull
    private Integer integer;

    private Long longNumber;

    @OneIsNotNull
    private SampleModelFieldParam sampleModelFieldParam;
}
