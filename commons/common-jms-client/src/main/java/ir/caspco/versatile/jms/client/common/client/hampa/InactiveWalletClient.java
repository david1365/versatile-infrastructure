package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.InactiveWalletRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.InactiveWalletResponse;
import org.springframework.stereotype.Component;

@Component
@JmsHeader(serviceId = "cardmanagement.message.InactiveWalletMsg")
public class InactiveWalletClient extends JmsClient<InactiveWalletRequest, InactiveWalletResponse> {
}
