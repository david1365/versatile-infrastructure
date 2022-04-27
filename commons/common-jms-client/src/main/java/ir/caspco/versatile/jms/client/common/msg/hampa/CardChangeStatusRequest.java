package ir.caspco.versatile.jms.client.common.msg.hampa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class CardChangeStatusRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String cardNumber;
}
