package ir.caspco.versatile.jms.client.common.vo.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class RevertTransactionEntranceVO {

    @NotEmpty
    @NotNull
    @NotBlank
    private String clientTrackingCode;

    @NotEmpty
    @NotNull
    @NotBlank
    @JsonProperty("dealRefrence")
    private String dealReference;
}
