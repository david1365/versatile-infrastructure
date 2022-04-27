package ir.caspco.versatile.rest.client.common.esb.payment;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

import ir.caspco.versatile.context.rest.client.annotations.ApplicationBaseUrl;
import ir.caspco.versatile.context.rest.client.annotations.JsonDateFormat;
import ir.caspco.versatile.context.rest.client.annotations.ResponsePath;
import ir.caspco.versatile.context.rest.client.annotations.ThrowException;
import ir.caspco.versatile.context.rest.client.common.esb.EsbException;
import ir.caspco.versatile.context.rest.client.interfaces.Post;
import ir.caspco.versatile.rest.client.RestClient;
import ir.caspco.versatile.rest.client.common.esb.payment.vo.PaymentEntranceVO;
import ir.caspco.versatile.rest.client.common.esb.payment.vo.PaymentResultVo;
import org.springframework.stereotype.Component;

@Component
@ApplicationBaseUrl("esb.uri")
@JsonDateFormat("yyyy-MM-dd HH:mm:ss")
@ThrowException(EsbException.class)
public class PaymentClient extends RestClient {

    @ResponsePath(servicePath = "esb.path.doPaymentWithoutLogin", outputType = PaymentResultVo.class)
    public Post<PaymentEntranceVO, Void, PaymentResultVo> doPaymentWithoutLogin;

}
