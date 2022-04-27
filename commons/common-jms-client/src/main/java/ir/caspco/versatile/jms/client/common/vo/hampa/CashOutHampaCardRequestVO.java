package ir.caspco.versatile.jms.client.common.vo.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CashOutHampaCardRequestVO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String cardNumber;

    private String segmentCode;

    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty("AccountNumber")
    private String accountNumber;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @NotBlank
    @NotEmpty
    @IsValidUUID
    private String uniqueTrackingCode;

    @NotNull
    private Boolean checkUniqueTrackingCode;
}
