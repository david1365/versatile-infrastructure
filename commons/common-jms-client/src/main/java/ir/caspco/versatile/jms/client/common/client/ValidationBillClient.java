package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.ResultNullPointerException;
import ir.caspco.versatile.jms.client.common.msg.ValidationBillRequest;
import ir.caspco.versatile.jms.client.common.msg.ValidationBillResponse;
import ir.caspco.versatile.jms.client.common.vo.BillDetailVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "payment.billPayment.message.ValidationBill")
public class ValidationBillClient extends JmsClient<ValidationBillRequest, ValidationBillResponse> {

    @SneakyThrows
    public BillDetailVO validate(ValidationBillRequest validationBillRequest) {

        ValidationBillResponse validationBillResponse =
                send(validationBillRequest)
                        .retrieve()
                        .result()
                        .orElseThrow(ResultNullPointerException::new);

        return validationBillResponse.getBillDetailDto();

    }

}
