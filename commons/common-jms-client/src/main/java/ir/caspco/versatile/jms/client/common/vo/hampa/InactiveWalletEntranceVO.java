package ir.caspco.versatile.jms.client.common.vo.hampa;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class InactiveWalletEntranceVO {

    @NotEmpty
    @NotNull
    @NotBlank
    private String cardNumber;

    @NotEmpty
    @NotNull
    @NotBlank
    private String segmentCode;

    private String accountNumber;
}
