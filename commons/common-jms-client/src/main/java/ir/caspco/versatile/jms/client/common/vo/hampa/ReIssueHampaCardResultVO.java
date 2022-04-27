package ir.caspco.versatile.jms.client.common.vo.hampa;


import ir.caspco.versatile.common.validation.annotations.OneIsNotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@OneIsNotNull
public class ReIssueHampaCardResultVO {

    private String cardNumber;
}
