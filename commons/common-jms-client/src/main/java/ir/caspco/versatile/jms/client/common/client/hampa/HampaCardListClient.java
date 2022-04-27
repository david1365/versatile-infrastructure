package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaCardListRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.HampaCardListResponse;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "cardmanagement.message.HampaCardListMsg")
public class HampaCardListClient extends JmsClient<HampaCardListRequest, HampaCardListResponse> {

}
