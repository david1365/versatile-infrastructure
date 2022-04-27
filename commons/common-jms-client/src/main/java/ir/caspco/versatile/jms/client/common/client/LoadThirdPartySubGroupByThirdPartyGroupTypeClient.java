package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.ThirdPartyGroupType;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartySubGroupByThirdPartyGroupTypeRequest;
import ir.caspco.versatile.jms.client.common.msg.LoadThirdPartySubGroupByThirdPartyGroupTypeResponse;
import ir.caspco.versatile.jms.client.common.vo.ThirdPartySubGroupVO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "payment.thirdParty.message.LoadThirdPartySubGroupByThirdPartyGroupType")
public class LoadThirdPartySubGroupByThirdPartyGroupTypeClient extends JmsClient<
        LoadThirdPartySubGroupByThirdPartyGroupTypeRequest, LoadThirdPartySubGroupByThirdPartyGroupTypeResponse> {

    public List<ThirdPartySubGroupVO> loadByThirdPartyGroupType(ThirdPartyGroupType thirdPartyGroupType) {

        LoadThirdPartySubGroupByThirdPartyGroupTypeRequest loadThirdPartySubGroupByThirdPartyGroupTypeRequest =
                LoadThirdPartySubGroupByThirdPartyGroupTypeRequest.builder()
                        .thirdPartyGroupType(thirdPartyGroupType.value())
                        .build();

        LoadThirdPartySubGroupByThirdPartyGroupTypeResponse loadThirdPartySubGroupByThirdPartyGroupTypeResponse =
                send(loadThirdPartySubGroupByThirdPartyGroupTypeRequest)
                        .retrieve()
                        .result()
                        .orElse(
                                LoadThirdPartySubGroupByThirdPartyGroupTypeResponse.builder()
                                        .thirdPartySubGroups(Collections.emptyList())
                                        .build()
                        );

        return loadThirdPartySubGroupByThirdPartyGroupTypeResponse.getThirdPartySubGroups();
    }
}
