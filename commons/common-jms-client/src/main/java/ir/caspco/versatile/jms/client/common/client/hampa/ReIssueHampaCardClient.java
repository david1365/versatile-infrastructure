package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.ReIssueHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.ReIssueHampaCardResponse;
import org.springframework.stereotype.Component;

@Component
@JmsHeader(serviceId = "cardmanagement.message.ReIssueHampaCardMsg")
public class ReIssueHampaCardClient extends JmsClient<ReIssueHampaCardRequest, ReIssueHampaCardResponse> {
}
