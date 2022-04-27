package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.CashOutHampaCardByAccountRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CashOutHampaCardByAccountResponse;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "cardmanagement.message.CashOutHampaCardByAccountNumbermsg")
public class CashOutHampaCardByAccountClient extends JmsClient<CashOutHampaCardByAccountRequest, CashOutHampaCardByAccountResponse> {

}
