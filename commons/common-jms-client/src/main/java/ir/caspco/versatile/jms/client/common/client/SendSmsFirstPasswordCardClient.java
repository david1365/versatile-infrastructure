package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.SendSmsFirstPasswordCardRequest;
import ir.caspco.versatile.jms.client.common.msg.SendSmsFirstPasswordCardResponse;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "cardmanagement.cms.SendSmsFirstPasswordCard")
public class SendSmsFirstPasswordCardClient extends JmsClient<SendSmsFirstPasswordCardRequest, SendSmsFirstPasswordCardResponse> {

}
