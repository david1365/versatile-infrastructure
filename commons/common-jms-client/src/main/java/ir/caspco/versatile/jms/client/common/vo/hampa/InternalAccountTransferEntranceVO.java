package ir.caspco.versatile.jms.client.common.vo.hampa;

import ir.caspco.versatile.common.validation.annotations.IsValidUUID;
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
public class InternalAccountTransferEntranceVO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String sourceCardNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    private String sourceSegmentCode;

    @NotNull
    @NotEmpty
    @NotBlank
    private String destinationCardNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    private String destinationSegmentCode;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Boolean checkUniqueTrackingCode;

    @NotNull
    @NotEmpty
    @NotBlank
    @IsValidUUID
    private String clientTrackingCode;
}