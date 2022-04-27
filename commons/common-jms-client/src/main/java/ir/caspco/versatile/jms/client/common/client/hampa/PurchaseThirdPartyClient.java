package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.PurchaseThirdPartyRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.PurchaseThirdPartyResponse;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 * @author Mohammad Javad Zahedi - 2021
 * mjavadzahedi0@gmail.com
 * 09377732642
 */


@Component
@JmsHeader(serviceId = PurchaseThirdPartyClient.SERVICE_ID)
public class PurchaseThirdPartyClient extends JmsClient<PurchaseThirdPartyRequest, PurchaseThirdPartyResponse> {

    protected static final String SERVICE_ID = "cardManagement.BuyThirdPartyByHampaCard";
}
