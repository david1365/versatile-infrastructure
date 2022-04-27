package ir.caspco.versatile.jms.client.common.msg.hampa;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class ChangeFirstHampaPasswordRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String cardNumber;

    @Builder.Default
    private Integer passwordType = 1;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}
