package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.CardTrackRequest;
import ir.caspco.versatile.jms.client.common.msg.CardTrackResponse;
import ir.caspco.versatile.jms.client.common.vo.CardPhysicVO;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "cardmanagement.message.HampaCardTrackMsg")
public class HampaCardTrackClient extends JmsClient<CardTrackRequest, CardTrackResponse> {

    public CardPhysicVO cardTrack(String cardNumber) {

        CardTrackRequest cardTrackRequest = CardTrackRequest.builder()
                .cardNumber(cardNumber)
                .build();

        CardTrackResponse cardTrackResponse = send(cardTrackRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);

        return cardTrackResponse.getCardPhysicDto();
    }
}
