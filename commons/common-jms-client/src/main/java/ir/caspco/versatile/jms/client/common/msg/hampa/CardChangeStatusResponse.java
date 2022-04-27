package ir.caspco.versatile.jms.client.common.msg.hampa;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CardChangeStatusResponse {

    private String message;
}
