package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusResponse;
import org.springframework.stereotype.Component;

@Component
@JmsHeader(serviceId = "cardmanagement.cms.ChangeCardHampaStatus")
public class CardChangeStatusClient extends JmsClient<CardChangeStatusRequest, CardChangeStatusResponse> {
}
