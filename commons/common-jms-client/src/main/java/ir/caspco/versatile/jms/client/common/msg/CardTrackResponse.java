package ir.caspco.versatile.jms.client.common.msg;

import ir.caspco.versatile.jms.client.common.vo.CardPhysicVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class CardTrackResponse {

    private CardPhysicVO cardPhysicDto;
}
