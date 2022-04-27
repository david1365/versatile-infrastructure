package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CustomerInfoException;
import ir.caspco.versatile.jms.client.common.msg.CustomerExistenceInquiryRequest;
import ir.caspco.versatile.jms.client.common.msg.CustomerExistenceInquiryResponse;
import ir.caspco.versatile.jms.client.common.vo.CompleteCustomerInfoVO;
import ir.caspco.versatile.jms.client.common.vo.UserRequestVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "channelManagement.MGCustomerExistenceInquiryMsg")
public class CustomerClient extends JmsClient<CustomerExistenceInquiryRequest, CustomerExistenceInquiryResponse> {

    @SneakyThrows
    public CompleteCustomerInfoVO getCustomerInfo(UserRequestVO userRequest) {

        CustomerExistenceInquiryRequest customerExistenceInquiryRequest = CustomerExistenceInquiryRequest.builder()
                .requestDto(userRequest)
                .build();


        CustomerExistenceInquiryResponse customerExistenceInquiryResponse =
                send(customerExistenceInquiryRequest)
                        .throwException()
                        .retrieve()
                        .result()
                        .orElseThrow(CustomerInfoException::new);

        CompleteCustomerInfoVO completeCustomerInfo = customerExistenceInquiryResponse.getResponseDto();

        //Insert default value if empty.
        String identificationNo = Optional.ofNullable(completeCustomerInfo.getIdentificationNo()).orElse("1");
        Date birthDate = Optional.ofNullable(completeCustomerInfo.getBirthDate()).orElse(new Date());
        String postalCode = Optional.ofNullable(completeCustomerInfo.getPostalCode()).orElse("1234567890");
        String address = Optional.ofNullable(completeCustomerInfo.getAddress()).orElse("address");

        completeCustomerInfo.setIdentificationNo(identificationNo);
        completeCustomerInfo.setBirthDate(birthDate);
        completeCustomerInfo.setPostalCode(postalCode);
        completeCustomerInfo.setAddress(address);

        return completeCustomerInfo;

    }

}
