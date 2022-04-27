package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.BuyProductByHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.BuyProductByHampaCardResponse;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "cardManagement.BuyProductByHampaCard")
public class BuyProductByHampaCardClient extends JmsClient<BuyProductByHampaCardRequest, BuyProductByHampaCardResponse> {

}
