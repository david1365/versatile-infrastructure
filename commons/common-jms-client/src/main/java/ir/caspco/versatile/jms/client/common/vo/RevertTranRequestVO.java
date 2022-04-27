package ir.caspco.versatile.jms.client.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class RevertTranRequestVO {

    private String clientTrackingCode;

    @JsonProperty("dealRefrence")
    private String dealReference;

}
