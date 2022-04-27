package ir.caspco.versatile.jms.client.common.vo.hampa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class HampaCardSegmentRequestVO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String cardNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    private String segmentCode;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Boolean checkUniqueTrackingCode;

    @NotNull
    @NotEmpty
    @NotBlank
    private String clientTrackingCode;

}
