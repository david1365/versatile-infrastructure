package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.ValidateActiveHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.ValidateActiveHampaCardResponse;
import org.springframework.stereotype.Component;

@Component
@JmsHeader(serviceId = "cardmanagement.cms.validateActiveHampaCard")
public class ValidateActiveHampaCardClient extends JmsClient<ValidateActiveHampaCardRequest, ValidateActiveHampaCardResponse> {
}
