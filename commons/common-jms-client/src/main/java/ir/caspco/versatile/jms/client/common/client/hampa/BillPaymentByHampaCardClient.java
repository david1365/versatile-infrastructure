package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.BillPaymentByHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.BillPaymentByHampaCardResponse;
import org.springframework.stereotype.Component;

@Component
@JmsHeader(serviceId = "cardManagement.BillPaymentByHampaCard")
public class BillPaymentByHampaCardClient extends JmsClient<BillPaymentByHampaCardRequest, BillPaymentByHampaCardResponse> {
}
