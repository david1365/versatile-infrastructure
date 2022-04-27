package ir.caspco.versatile.jms.client.common.client.hampa;


import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.hampa.TranInqRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.TranInqResponse;
import org.springframework.stereotype.Component;

/**
 * @author sadeghi
 * date:1400/08/30
 * TransactionInquiryService
 */

@Component
@JmsHeader(serviceId = "cardmanagement.message.TransactionInquiryMsg")
public class TranInqClient extends JmsClient<TranInqRequest, TranInqResponse> {
}

