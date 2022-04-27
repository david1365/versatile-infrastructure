package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.RevertTranRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.RevertTranResponse;
import org.springframework.stereotype.Component;


@Component
@JmsHeader(serviceId = "cardmanagement.message.RevertTransactionMsg")
public class RevertTranClient extends JmsClient<RevertTranRequest, RevertTranResponse> {
}
