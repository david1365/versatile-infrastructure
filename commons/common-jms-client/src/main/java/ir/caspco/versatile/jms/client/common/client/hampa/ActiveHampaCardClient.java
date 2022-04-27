package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusResponse;
import org.springframework.stereotype.Component;

/**
 * Date: 2/1/2022
 * User: khalili_f
 */
@Component
@JmsHeader(serviceId = "cardmanagement.cms.ActiveHampaCard")
public class ActiveHampaCardClient extends JmsClient<CardChangeStatusRequest, CardChangeStatusResponse> {
}
