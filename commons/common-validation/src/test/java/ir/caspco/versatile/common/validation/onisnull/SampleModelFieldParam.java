package ir.caspco.versatile.common.validation.onisnull;

import ir.caspco.versatile.common.validation.annotations.OneIsFull;
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
public class SampleModelFieldParam {
    @OneIsFull
    private String string;

    @OneIsFull
    private Integer integer;
}
